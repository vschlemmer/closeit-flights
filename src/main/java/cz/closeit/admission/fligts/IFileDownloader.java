package cz.closeit.admission.fligts;

import java.net.URL;

public interface IFileDownloader {

    /**
     * Downloads csv file with statistics from the given url
     * @param url URL
     * @param filePath String
     * @return String the name of the downloaded file
     */
    public String download(URL url, String filePath);

}
