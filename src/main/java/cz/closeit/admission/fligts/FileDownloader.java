package cz.closeit.admission.fligts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileDownloader implements IFileDownloader {
    private final long BUFFER_SIZE = 1024 * 1024;

    /**
     * Downloads csv file with statistics of the given year
     * @param url URL
     * @param filePath String
     * @return String the name of the downloaded file
     */
    @Override
    public String download(URL url, String filePath) {
        try (
            ReadableByteChannel channel = Channels.newChannel(url.openStream());
            FileOutputStream stream = new FileOutputStream(filePath)
        ) {
            long fileSize = getFileSize(url);
            System.out.println("Downloading the file with the statistics, please wait...");
            System.out.print("Progress: 0%\r");

            // using a buffer size in a cycle in order to show a progress
            long bytesTransferred;
            long offset = 0;
            while ((bytesTransferred = stream.getChannel().transferFrom(channel, offset, BUFFER_SIZE)) > 0) {
                offset += bytesTransferred;
                System.out.print("Progress: " + Util.getProgress(offset, fileSize) + "%\r");
            }
            System.out.println();

            return filePath;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Data for given year could not be found.");
            // todo: log the stack trace
        } catch (IOException ioe) {
            System.out.println("Something went wrong while downloading the statistics file.");
            // todo: log the stack trace
        }

        return null;
    }

    private long getFileSize(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).setRequestMethod("HEAD");
        }
        conn.getInputStream();
        return conn.getContentLength();
    }

}
