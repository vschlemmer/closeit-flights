package cz.closeit.admission.fligts;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.*;

public class FileDecompressor implements IFileDecompressor {
    private final int BUFFER_SIZE = 1024 * 1024;

    @Override
    public boolean decompress(String filePath) {
        try (
            FileInputStream in = new FileInputStream(filePath);
            FileOutputStream out = new FileOutputStream(getFilePathWithoutSuffix(filePath));
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in)
        ) {
            File f = new File(filePath);
            long fileSize = f.length();
            System.out.println("Decompressing the file with the statistics, please wait...");
            System.out.print("Progress: 0%\r");

            byte[] buffer = new byte[BUFFER_SIZE];
            int n = 0;
            long offset = 0;
            while ((n = bzIn.read(buffer)) != -1) {
                out.write(buffer, 0, n);
                offset += n;
                System.out.print("Progress: " + Util.getProgress(offset, fileSize) + "%\r");
            }
            return true;
        } catch (FileNotFoundException fnfe) {
            System.out.println("The statistics file wasn't found during decompression, the file path was " + filePath + ".");
            // todo: log the stack trace
        } catch (IOException ioe) {
            System.out.println("Something went wrong while decompressing the statistics file.");
            // todo: log the stack trace
        }

        return false;
    }

    private String getFilePathWithoutSuffix(String filePath) {
        if (filePath.contains(".")) {
            return filePath.substring(0, filePath.lastIndexOf('.'));
        }

        return "";
    }

}
