package cz.closeit.admission.fligts.test;

import cz.closeit.admission.fligts.FileHelper;
import cz.closeit.admission.fligts.IFileDecompressor;
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
    private final String FILE_PREFIX = "src/test/resources/";

    @Mock
    private IFileDownloader fileDownloader;

    @Mock
    private IFileDecompressor fileDecompressor;

    @Test
    public void testLoadFileDownload() {
        // check that the file is downloaded when not found locally
        String originalFilePath = FILE_PREFIX + "1987.csv.bz2";
        when(fileDownloader.download((URL) any(), anyString())).thenReturn(originalFilePath);
        FileHelper fileHelper = new FileHelper(fileDownloader, fileDecompressor);
        String filePath = fileHelper.loadFile("1987", FILE_PREFIX);
        assertEquals(originalFilePath, filePath);
        verify(fileDownloader, times(1)).download((URL) any(), anyString());
    }

    @Test
    public void testLoadFileLocal() {
        // check that the file is retrieved from a local folder if found
        FileHelper fileHelper = new FileHelper(fileDownloader, fileDecompressor);
        String filePath = fileHelper.loadFile("1988", FILE_PREFIX);
        assertEquals(FILE_PREFIX + "1988.csv.bz2", filePath);
        verify(fileDownloader, never()).download((URL) any(), anyString());
    }

    @Test
    public void testLoadFileNonNumericYear() {
        // check that the file is retrieved from a local folder if found
        FileHelper fileHelper = new FileHelper(fileDownloader, fileDecompressor);
        String filePath = fileHelper.loadFile("asdf", FILE_PREFIX);
        assertEquals(null, filePath);
        verify(fileDownloader, never()).download((URL) any(), anyString());
    }

    @Test
    public void testLoadFileNonExistingYear() {
        // check that the file is retrieved from a local folder if found
        FileHelper fileHelper = new FileHelper(fileDownloader, fileDecompressor);
        String filePath = fileHelper.loadFile("1900", FILE_PREFIX);
        assertEquals(null, filePath);
        verify(fileDownloader, times(1)).download((URL) any(), anyString());
    }

}
