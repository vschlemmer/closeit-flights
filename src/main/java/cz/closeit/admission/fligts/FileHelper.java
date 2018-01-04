package cz.closeit.admission.fligts;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileHelper {
    private final String URL_PREFIX = "http://stat-computing.org/dataexpo/2009/";
    private final String URL_POSTFIX = ".csv.bz2";
    private IFileDownloader fileDownloader;

    public FileHelper(IFileDownloader fileDownloader) {
        this.fileDownloader = fileDownloader;
    }

    /**
     * Loads file with statistics of the given year.
     * In case the file is not found locally, it is downloaded
     * @param year String
     * @param prefix String
     * @return String the name of the loaded file
     */
    public String loadFile(String year, String prefix) {
        String filename = getFileName(year);
        URL resourceUrl = getClass().getResource(prefix + filename);
        if (resourceUrl != null) {
            File file = new File(resourceUrl.getPath());
            if (file.exists() && !file.isDirectory()) {
                return filename;
            }
        }

        URL url = null;
        try {
            url = new URL(getCompleteUrl(year));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (url != null) {
            return fileDownloader.download(url, filename);
        }

        return null;
    }

    private String getCompleteUrl(String year) {
        return URL_PREFIX + getFileName(year);
    }

    private String getFileName(String year) {
        return year + URL_POSTFIX;
    }

}
