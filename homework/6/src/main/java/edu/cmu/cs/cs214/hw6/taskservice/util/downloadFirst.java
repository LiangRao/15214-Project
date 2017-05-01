package edu.cmu.cs.cs214.hw6.taskservice.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by raoliang on 4/29/17.
 */
public class downloadFirst{

//
//    @Override
//    public void stepRun(Object data, String workingDirector) throws IOException, ClassNotFoundException, InterruptedException{
//        //download sources from Github mirror and unzip
//        System.out.println("downloading sources");
//        URL website = new URL("https://github.com/findbugsproject/findbugs/archive/master.zip");
//        ZipInputStream zipIn = new ZipInputStream(website.openStream());
//        ZipEntry entry = zipIn.getNextEntry();
//        // iterates over entries in the zip file
//        while (entry != null) {
//            File filePath = new File(workingDirector, entry.getName());
//            if (!entry.isDirectory()) {
//                // if the entry is a file, extracts it
//                System.out.println("> extracting " + filePath);
//                extractFile(zipIn, filePath);
//            } else {
//                // if the entry is a directory, make the directory
//                filePath.mkdir();
//            }
//            zipIn.closeEntry();
//            entry = zipIn.getNextEntry();
//        }
//        zipIn.close();
//
//        //download dependencies
//        System.out.println("downloading dependencies");
//        File jarDir = new File(workingDirector, "jars");
//        jarDir.mkdir();
//        Map<File, URL> dependencies = new HashMap<>();
//        addMavenDependency(dependencies, jarDir, "dom4j", "dom4j", "1.6.1");
//        addMavenDependency(dependencies, jarDir, "net.jcip", "jcip-annotations", "1.0");
//        addMavenDependency(dependencies, jarDir, "com.google.code.findbugs", "jsr305", "2.0.1");
//        addMavenDependency(dependencies, jarDir, "com.google.code.findbugs", "jFormatString", "2.0.1");
//        addMavenDependency(dependencies, jarDir, "org.apache.bcel", "bcel", "6.0");
//        addMavenDependency(dependencies, jarDir, "org.ow2.asm", "asm-debug-all", "6.0_ALPHA");
//        addMavenDependency(dependencies, jarDir, "org.ow2.asm", "asm-commons", "6.0_ALPHA");
//        addMavenDependency(dependencies, jarDir, "commons-lang", "commons-lang", "2.6");
//
//        Map<File, URL> testDependencies = new HashMap<>();
//        testDependencies.putAll(dependencies);
//        addMavenDependency(testDependencies, jarDir, "junit", "junit", "4.11");
//        addMavenDependency(testDependencies, jarDir, "org.hamcrest", "hamcrest-core", "1.3");
//        addMavenDependency(testDependencies, jarDir, "jdepend", "jdepend", "2.9.1");
//        addMavenDependency(testDependencies, jarDir, "ant", "ant", "1.6.5");
//
//        for (Map.Entry<File, URL> dependency : testDependencies.entrySet()) {
//            System.out.println("> downloading " + dependency.getKey());
//            download(dependency.getValue(), dependency.getKey());
//        }
//
//
//        File outDir = new File(workingDirector, "class/");
//        outDir.mkdir();
//        File testOutDir = new File(workingDirector, "testClass/");
//        testOutDir.mkdir();
//        File srcDir = new File(workingDirector, "findbugs-master/findbugs/src/java");
//        File src5Dir = new File(workingDirector, "findbugs-master/findbugs/src/gui");
//        File testDir = new File(workingDirector, "findbugs-master/findbugs/src/junit");
//    }
//    private File addMavenDependency(Map<File, URL> dependencies, File jarDir, String group, String name, String version) throws MalformedURLException {
//        File jarFile = new File(jarDir, name + "-" + version + ".jar");
//        dependencies.put(jarFile, createMavenDep(group, name, version));
//        return jarFile;
//    }
//
//    private void download(URL source, File target) throws IOException {
//        ReadableByteChannel rbc = Channels.newChannel(source.openStream());
//        FileOutputStream fos = new FileOutputStream(target);
//        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//    }
//
//    private static final int BUFFER_SIZE = 4096;
//    /**
//     * Extracts a zip entry (file entry)
//     */
//    private void extractFile(ZipInputStream zipIn, File filePath) throws IOException {
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
//        byte[] bytesIn = new byte[BUFFER_SIZE];
//        int read = 0;
//        while ((read = zipIn.read(bytesIn)) != -1) {
//            bos.write(bytesIn, 0, read);
//        }
//        bos.close();
//    }
//
//    private URL createMavenDep(String group, String name, String version) throws MalformedURLException {
//        return new URL("http://repo.maven.apache.org/maven2/" + group.replace('.', '/') + "/" + name + "/" + version + "/" + name + "-" + version + ".jar");
//
//    }
}
