package cz.closeit.admission.fligts;

import java.net.URL;

public interface IFileDownloader {

    /**
     * Downloads csv file with statistics of the given year
     * @param url URL
     * @param fileName String
     * @return String the name of the downloaded file
     */
    public String download(URL url, String fileName);

}
