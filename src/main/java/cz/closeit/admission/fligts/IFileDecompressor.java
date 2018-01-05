package cz.closeit.admission.fligts;

public interface IFileDecompressor {

    /**
     * Decompresses bz2 file
     * @param filePath String
     * @return boolean true if the decompression was without errors
     */
    boolean decompress(String filePath);

}
