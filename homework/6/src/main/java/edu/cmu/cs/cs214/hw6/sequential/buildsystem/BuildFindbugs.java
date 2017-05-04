package edu.cmu.cs.cs214.hw6.sequential.buildsystem;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by ckaestne on 3/27/17.
 */
public class BuildFindbugs implements Task,Serializable{
    private String taskName;
    private static final File WORKINGDIRECTORY = new File("wd");
    private Map<File, URL> dependencies = new HashMap<>();
    private Map<File, URL> testDependencies = new HashMap<>();
    private File outDir = new File(WORKINGDIRECTORY, "class/");
    private File testOutDir = new File(WORKINGDIRECTORY, "testClass/");
    private File srcDir = new File(WORKINGDIRECTORY, "findbugs-master/findbugs/src/java");
    private File src5Dir = new File(WORKINGDIRECTORY, "findbugs-master/findbugs/src/gui");
    private File testDir = new File(WORKINGDIRECTORY, "findbugs-master/findbugs/src/junit");
    private List<String> testClasses = new ArrayList<>();

    /**
     * Set up the working directory
     * @return the log of sub-task
     */
    public String setUp(){
        System.out.println("Setting up working directory");
        if (WORKINGDIRECTORY.exists())
            WORKINGDIRECTORY.delete();
        if (!WORKINGDIRECTORY.exists())
            WORKINGDIRECTORY.mkdir();
        WORKINGDIRECTORY.deleteOnExit();
        return "";
    }

    /**
     * Download sources from Github mirror and unzip
     * @return the log of the sub-task
     * @throws IOException the IO Exception
     */
    public String downloadFirst() throws IOException {
        StringBuilder sb = new StringBuilder();
        System.out.println("downloading sources\n");
        sb.append("downloading sources");
        URL website = new URL("https://github.com/findbugsproject/findbugs/archive/master.zip");
        ZipInputStream zipIn = new ZipInputStream(website.openStream());
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            File filePath = new File(WORKINGDIRECTORY, entry.getName());
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                System.out.println("> extracting " + filePath);
                sb.append("> extracting " + filePath+"\n");
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                filePath.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        return sb.toString();
    }
    /**
     * Download dependencies
     * @return the log of the sub-task
     * @throws IOException the IO Exception
     */
    public String downloadSecond() throws IOException {
        StringBuilder sb = new StringBuilder();
        System.out.println("downloading dependencies");
        sb.append("downloading dependencies\n");
        File jarDir = new File(WORKINGDIRECTORY, "jars");
        jarDir.mkdir();

        addMavenDependency(dependencies, jarDir, "dom4j", "dom4j", "1.6.1");
        addMavenDependency(dependencies, jarDir, "net.jcip", "jcip-annotations", "1.0");
        addMavenDependency(dependencies, jarDir, "com.google.code.findbugs", "jsr305", "2.0.1");
        addMavenDependency(dependencies, jarDir, "com.google.code.findbugs", "jFormatString", "2.0.1");
        addMavenDependency(dependencies, jarDir, "org.apache.bcel", "bcel", "6.0");
        addMavenDependency(dependencies, jarDir, "org.ow2.asm", "asm-debug-all", "6.0_ALPHA");
        addMavenDependency(dependencies, jarDir, "org.ow2.asm", "asm-commons", "6.0_ALPHA");
        addMavenDependency(dependencies, jarDir, "commons-lang", "commons-lang", "2.6");

        testDependencies.putAll(dependencies);
        addMavenDependency(testDependencies, jarDir, "junit", "junit", "4.11");
        addMavenDependency(testDependencies, jarDir, "org.hamcrest", "hamcrest-core", "1.3");
        addMavenDependency(testDependencies, jarDir, "jdepend", "jdepend", "2.9.1");
        addMavenDependency(testDependencies, jarDir, "ant", "ant", "1.6.5");

        for (Map.Entry<File, URL> dependency : testDependencies.entrySet()) {
            System.out.println("> downloading " + dependency.getKey());
            sb.append("> downloading " + dependency.getKey()+"\n");
            download(dependency.getValue(), dependency.getKey());
        }
        outDir.mkdir();
        testOutDir.mkdir();
        return sb.toString();
    }

    /**
     * Compiling findbugs
     * @return the log of the sub-task
     * @throws IOException
     * @throws InterruptedException
     */
    public String compileFirst() throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        System.out.println("compiling findbugs");
        sb.append("compiling findbugs\n");
        {
            List<String> compilerCommand = createCompilerCommand(testDependencies, outDir, srcDir, src5Dir);
            List<String> command = new ArrayList<>(compilerCommand);
            for (File javaFile : findJavaFiles(new File(WORKINGDIRECTORY, "findbugs-master/findbugs/src/java")))
                command.add(javaFile.getPath());
            int exitVal = new ProcessBuilder(command).redirectOutput(new File("out.txt")).redirectError(new File("err.txt")).start().waitFor();
            assert exitVal == 0 : "compilation failed with " + exitVal;
        }
        return sb.toString();
    }

    /**
     * Compiling tests
     * @return the log of the sub-task
     * @throws IOException
     * @throws InterruptedException
     */
    public String compileSecond() throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        List<String> testCompilerCommand = createTestCompilerCommand(testDependencies, testOutDir, testDir, outDir);
        System.out.println("compiling tests");
        sb.append("compiling tests\n");

        for (File javaFile : findJavaFiles(testDir)) {
            System.out.println("> compiling " + javaFile);
            sb.append("> compiling " + javaFile+"\n");
            List<String> command = new ArrayList<>(testCompilerCommand);
            command.add(javaFile.getPath());

            int exitVal = new ProcessBuilder(command)/*.redirectError(new File("err.txt"))*/.start().waitFor();
            assert exitVal == 0 : "compilation of " + javaFile + "failed with " + exitVal;

            String testClass = extractClassName(testDir, javaFile);
            testClasses.add(testClass);
        }
        return sb.toString();
    }

    /**
     * Running test and create a .jar file
     * @return the log of the sub-task
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public String run() throws IOException, ClassNotFoundException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        System.out.println("running tests");
        sb.append("running tests\n");
        ClassLoader testClassLoader = getTestClassLoader(dependencies, outDir, testOutDir);
        for (String testClass : testClasses) {
            System.out.println("> running tests in " + testClass);
            sb.append("> running tests in " + testClass+"\n");
            Result result = JUnitCore.runClasses(testClassLoader.loadClass(testClass));
            totalTestsRun += result.getRunCount();
            totalTestsFailed += result.getFailureCount();
            totalTestsIgnored += result.getIgnoreCount();
            totalTestTime += result.getRunTime();
            testFailures.addAll(result.getFailures());
        }

        System.out.printf("test results:\n%d tests executed\n%d tests failed\n%d tests ignored\n%d ms execution time\n", totalTestsRun, totalTestsFailed, totalTestsIgnored, totalTestTime);
        System.out.println("failed tests: ");
        sb.append("test results:\n"+totalTestsRun+" tests executed\n"+totalTestsFailed+" tests failed\n"+totalTestsIgnored+" tests ignored\n"+totalTestTime+" ms execution time\n");
        sb.append("failed tests: ");
        for (Failure f : testFailures) {
            System.out.printf(" - %s\n", f.toString());
            sb.append("-" + f.toString()+"\n");
        }

        System.out.println("creating jar file");
        String[] jarCommand = {"jar", "cf", new File(WORKINGDIRECTORY, "findbugs.jar").getPath(), outDir.getPath()};
        int exitVal = new ProcessBuilder(jarCommand)/*.redirectError(new File("err.txt"))*/.start().waitFor();
        assert exitVal == 0 : "creation of jar file failed with " + exitVal;
        return sb.toString();
    }

    private int totalTestsRun = 0;
    private int totalTestsFailed = 0;
    private int totalTestsIgnored = 0;
    private int totalTestTime = 0;
    private List<Failure> testFailures = new ArrayList<>();

    private ClassLoader getTestClassLoader(Map<File, URL> dependencies, File srcDir, File testDir) throws MalformedURLException {
        URL[] urls = new URL[dependencies.size() + 2];
        urls[0] = testDir.toURI().toURL();
        urls[1] = srcDir.toURI().toURL();
        int i = 1;
        for (File f : dependencies.keySet())
            urls[++i] = f.toURI().toURL();
        return new URLClassLoader(urls);
    }

    private String extractClassName(File testDir, File classFile) {
        String result = classFile.getName().substring(0, classFile.getName().length() - 5);
        classFile = classFile.getParentFile();
        while (!classFile.equals(testDir)) {
            result = classFile.getName() + "." + result;
            classFile = classFile.getParentFile();
        }
        return result;
    }


    private Class<?> loadTest(String testClassName, File... dirs) throws MalformedURLException, ClassNotFoundException {
        URL[] urls = new URL[dirs.length];
        for (int i = 0; i < dirs.length; i++)
            urls[i] = dirs[i].toURI().toURL();
        return new URLClassLoader(urls).loadClass(testClassName);

    }

    private List<String> createBasicCompilerCommand(File outDir, File srcDir) {
        List<String> compilerCommand = new ArrayList<>();
        compilerCommand.add("javac");
        compilerCommand.add("-Xlint:unchecked");
        compilerCommand.add("-sourcepath");
        compilerCommand.add(srcDir.getPath());
        compilerCommand.add("-d");
        compilerCommand.add(outDir.getPath());
        return compilerCommand;
    }

    private List<String> createTestCompilerCommand(Map<File, URL> dependencies, File outDir, File srcDir, File mainClassDir) {
        List<String> compilerCommand = createBasicCompilerCommand(outDir, srcDir);
        compilerCommand.add("-classpath");
        String classPath = outDir.getPath();
        for (File dep : dependencies.keySet())
            classPath += ":" + dep.getPath();
        classPath += ":" + mainClassDir.getPath();
        compilerCommand.add(classPath);
        return compilerCommand;
    }

    private List<String> createCompilerCommand(Map<File, URL> dependencies, File outDir, File srcDir, File src5Dir) {
        List<String> compilerCommand = createBasicCompilerCommand(outDir, srcDir);
        compilerCommand.add("-classpath");
        String classPath = outDir.getPath();
        for (File dep : dependencies.keySet())
            classPath += ":" + dep.getPath();
        compilerCommand.add(classPath);
        compilerCommand.add("-sourcepath");
        compilerCommand.add(src5Dir.getPath());
        return compilerCommand;
    }

    private Collection<File> findJavaFiles(File sourceDir) {
        List<File> result = new ArrayList<>();
        if (sourceDir.exists())
            for (File f : sourceDir.listFiles())
                if (f.isDirectory())
                    result.addAll(findJavaFiles(f));
                else if (f.getName().toLowerCase().endsWith(".java"))
                    result.add(f);
        return result;
    }

    private Collection<File> findClassFiles(File sourceDir) {
        List<File> result = new ArrayList<>();
        if (sourceDir.exists())
            for (File f : sourceDir.listFiles())
                if (f.isDirectory())
                    result.addAll(findJavaFiles(f));
                else if (f.getName().toLowerCase().endsWith(".class"))
                    result.add(f);
        return result;
    }

    private File addMavenDependency(Map<File, URL> dependencies, File jarDir, String group, String name, String version) throws MalformedURLException {
        File jarFile = new File(jarDir, name + "-" + version + ".jar");
        dependencies.put(jarFile, createMavenDep(group, name, version));
        return jarFile;
    }


    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Extracts a zip entry (file entry)
     */
    private void extractFile(ZipInputStream zipIn, File filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    private void download(URL source, File target) throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(source.openStream());
        FileOutputStream fos = new FileOutputStream(target);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    private URL createMavenDep(String group, String name, String version) throws MalformedURLException {
        return new URL("http://repo.maven.apache.org/maven2/" + group.replace('.', '/') + "/" + name + "/" + version + "/" + name + "-" + version + ".jar");

    }

    @Override
    public List<Set<String>> getFunctionName() {
        List<Set<String>> functionList = new ArrayList<>();
        Set<String> setUp = new HashSet<>();
        setUp.add("setUp");
        Set<String> download = new HashSet<>();
        download.add("downloadFirst");
        download.add("downloadSecond");
        Set<String> compile = new HashSet<>();
        compile.add("compileFirst");
        compile.add("compileSecond");
        Set<String> run = new HashSet<>();
        run.add("run");
        functionList.add(setUp);
        functionList.add(download);
        functionList.add(compile);
        functionList.add(run);
        return functionList;
    }

    @Override
    public File getWorkingDirectory() {
        return WORKINGDIRECTORY;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void setTaskName(String name) {
        taskName = name;
    }


}
