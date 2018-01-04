package cz.closeit.admission.fligts.test;

import cz.closeit.admission.fligts.FileHelper;
import cz.closeit.admission.fligts.IFileDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.net.URL;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FileHelperTest {
    private String filePrefix = "src/test/resources/";

    @Mock
    private IFileDownloader fileDownloader;

    @Test
    public void testLoadFileDownload() {
        // check that the file is downloaded when not found locally
        String originalFilePath = filePrefix + "1987.csv.bz2";
        when(fileDownloader.download((URL) any(), anyString())).thenReturn(originalFilePath);
        FileHelper fileHelper = new FileHelper(fileDownloader);
        String filePath = fileHelper.loadFile("1987", filePrefix);
        assertEquals(originalFilePath, filePath);
        verify(fileDownloader, times(1)).download((URL) any(), anyString());
    }

    @Test
    public void testLoadFileLocal() {
        // check that the file is retrieved from a local folder if found
        FileHelper fileHelper = new FileHelper(fileDownloader);
        String filePath = fileHelper.loadFile("1988", filePrefix);
        assertEquals(filePrefix + "1988.csv.bz2", filePath);
        verify(fileDownloader, never()).download((URL) any(), anyString());
    }

}
