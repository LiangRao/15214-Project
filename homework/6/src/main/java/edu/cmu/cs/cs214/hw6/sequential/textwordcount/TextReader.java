package edu.cmu.cs.cs214.hw6.sequential.textwordcount;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * The class to load a specific file from given url, and then
 * generate a Scanner for that file
 */
public class TextReader {
    /**
     * Generate a Scanner for the given file
     * @param fileName the name of the file to be scanned
     * @return a Scanner for the given file
     */
    public static Scanner openFile( String fileName ) {
        Scanner result = null;
        try {
            InputStream istream = new FileInputStream( fileName );
            result = new Scanner( istream );
        }
        catch ( FileNotFoundException fnfe ) {
            System.err.println( fnfe );
            System.err.println( "File \"" + fileName + "\" does not exist." );
            System.exit( 1 );
        }
        return result;
    }

    /**
     * Down load file from website to local
     * @param remoteFilePath the url of remote file
     * @param localFilePath the local path to store the file
     */
    public void downloadFile(String remoteFilePath, String localFilePath)
    {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try
        {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
