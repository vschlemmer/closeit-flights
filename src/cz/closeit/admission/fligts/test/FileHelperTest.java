package cz.closeit.admission.fligts.test;

import cz.closeit.admission.fligts.FileHelper;
import cz.closeit.admission.fligts.IFileDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FileHelperTest {
    private String filePrefix = "./test/";

    @Mock
    private IFileDownloader fileDownloader;

    @Test
    public void testLoadFileDownload() {
        // check that the file is downloaded when not found locally
        when(fileDownloader.download(any(), anyString())).thenReturn("1987.csv.bz2");
        FileHelper fileHelper = new FileHelper(fileDownloader);
        String fileName = fileHelper.loadFile("1987", filePrefix);
        assertEquals("1987.csv.bz2", fileName);
        verify(fileDownloader, times(1)).download(any(), anyString());
    }

    @Test
    public void testLoadFileLocal() {
        // check that the file is retrieved from local filesystem if found
        FileHelper fileHelper = new FileHelper(fileDownloader);
        String fileName = fileHelper.loadFile("1988", filePrefix);
        assertEquals("1988.csv.bz2", fileName);
        verify(fileDownloader, never()).download(any(), anyString());
    }

}
