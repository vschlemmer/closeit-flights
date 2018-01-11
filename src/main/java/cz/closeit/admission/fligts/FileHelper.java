package cz.closeit.admission.fligts;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileHelper {
    private final String URL_PREFIX = "http://stat-computing.org/dataexpo/2009/";
    private final String FILE_SUFFIX = "csv";
    private final String ARCHIVE_SUFFIX = "bz2";

    private IFileDownloader fileDownloader;
    private IFileDecompressor fileDecompressor;

    public FileHelper(IFileDownloader fileDownloader, IFileDecompressor fileDecompressor) {
        this.fileDownloader = fileDownloader;
        this.fileDecompressor = fileDecompressor;
    }

    /**
     * Loads file with statistics of the given year.
     * In case the file is not found locally, it is downloaded
     * @param year String
     * @param prefix String
     * @return String the path to the loaded file
     */
    public String loadFile(String year, String prefix) {
        if (!isNumeric(year)) {
            System.out.println("Given year must be a number.");
            return null;
        }

        String filePath = prefix + year + getCompleteSuffix();

        if (!(new File(prefix).mkdirs())) {
            System.out.println("Could not create a folder for storing the statistics data.");
        }

        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            System.out.println("Using previously downloaded file.");
            return filePath;
        }

        URL url = null;
        try {
            url = new URL(URL_PREFIX + year + getCompleteSuffix());
        } catch (IOException e) {
            System.out.println("Something went wrong while downloading the statistics file.");
            // todo: log the stack trace
        }

        if (url != null) {
            return fileDownloader.download(url, filePath);
        }

        return null;
    }

    /**
     * Decompresses the given archive
     * In case the corresponding decompressed file already exists, it is returned
     * @param filePath String
     * @return String the path to the decompressed file
     */
    public String decompressFile(String filePath) {
        if (isSuffixInvalid(filePath, ARCHIVE_SUFFIX)) {
            return "";
        }

        String filePathWithoutSuffix = Util.getFilePathWithoutSuffix(filePath);
        if (isSuffixInvalid(filePathWithoutSuffix, FILE_SUFFIX)) {
            return "";
        }

        File file = new File(filePathWithoutSuffix);
        if (file.exists() && !file.isDirectory()) {
            System.out.println("Using previously decompressed file.");
            return filePathWithoutSuffix;
        }

        if (fileDecompressor.decompress(filePath)) {
            return filePathWithoutSuffix;
        }

        return "";
    }

    private boolean isNumeric(String year) {
        try {
            int num = Integer.parseInt(year);
        } catch(NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private boolean isSuffixInvalid(String filePath, String expectedSuffix) {
        String suffix = filePath.substring(filePath.lastIndexOf('.') + 1, filePath.length());
        if (!expectedSuffix.equals(suffix)) {
            System.out.println(expectedSuffix + " suffix expected, encountered '" + suffix + "'.");
            return true;
        }

        return false;
    }

    private String getCompleteSuffix() {
        return "." + FILE_SUFFIX + "." + ARCHIVE_SUFFIX;
    }

}
