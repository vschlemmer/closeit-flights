package cz.closeit.admission.fligts;

public interface IFileDecompressor {

    /**
     * Decompresses bz2 file
     * @param filePath String
     * @return String the name of the file
     */
    boolean decompress(String filePath);

}
