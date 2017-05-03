package edu.cmu.cs.cs214.hw6.sequential.textwordcount;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TextReader {
    /**
     * Produce a Scanner instance connected to the given file.
     * If the file cannot be opened, this method will report
     * it on standard error, and then terminate the program
     * with a status of 1.
     * @param fileName the name of the file to be scanned
     * @return a Scanner instance on the file. (Please close it when done!)
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
