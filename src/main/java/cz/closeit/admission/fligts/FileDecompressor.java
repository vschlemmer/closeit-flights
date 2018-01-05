package cz.closeit.admission.fligts;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import java.io.*;

public class FileDecompressor implements IFileDecompressor {
    private final int BUFFER_SIZE = 1024 * 1024;

    @Override
    public boolean decompress(String filePath) {
        try (
            FileInputStream in = new FileInputStream(filePath);
            FileOutputStream out = new FileOutputStream(Util.getFilePathWithoutSuffix(filePath));
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in)
        ) {
            System.out.println("Decompressing the file with the statistics, please wait...");
            System.out.print("Progress: 0 MB\r");
            final byte[] buffer = new byte[BUFFER_SIZE];
            int n;
            long bytesProcessed = 0;
            while ((n = bzIn.read(buffer)) > 0) {
                // could not add percentage progress because we don't know the size of the uncompressed
                // target file so the progress would always end at more than 100%
                out.write(buffer, 0, n);
                bytesProcessed += n;
                System.out.print("Progress: " + (bytesProcessed / (1000 * 1000)) + " MB\r");
            }
            System.out.println();
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

}
