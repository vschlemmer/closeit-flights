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
            final byte[] buffer = new byte[BUFFER_SIZE];
            int n;
            while ((n = bzIn.read(buffer)) > 0) {
                out.write(buffer, 0, n);
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

}
