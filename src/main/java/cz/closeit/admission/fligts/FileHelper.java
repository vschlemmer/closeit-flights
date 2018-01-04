package cz.closeit.admission.fligts;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileHelper {
    private final String URL_PREFIX = "http://stat-computing.org/dataexpo/2009/";
    private final String FILE_POSTFIX = ".csv.bz2";
    private IFileDownloader fileDownloader;

    public FileHelper(IFileDownloader fileDownloader) {
        this.fileDownloader = fileDownloader;
    }

    /**
     * Loads file with statistics of the given year.
     * In case the file is not found locally, it is downloaded
     * @param year String
     * @param prefix String
     * @return String the path to the loaded file
     */
    public String loadFile(String year, String prefix) {
        String filePath = prefix + year + FILE_POSTFIX;
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            return filePath;
        }

        URL url = null;
        try {
            url = new URL(URL_PREFIX + year + FILE_POSTFIX);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (url != null) {
            return fileDownloader.download(url, filePath);
        }

        return null;
    }

}
