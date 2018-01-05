package cz.closeit.admission.fligts.test;

import cz.closeit.admission.fligts.DelayAnalyzer;
import cz.closeit.admission.fligts.IDelayAnalyzer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The src/test/resources/1989.csv file contains
 *  - 3 flights to HNL airport - all non-cancelled
 *  - 4 flights to LAX airport - one of them is cancelled
 */
public class DelayAnalyzerTest {
    private final String FILE_PREFIX = "src/test/resources/";
    private IDelayAnalyzer delayAnalyzer = new DelayAnalyzer();

    @Test
    public void testGetAverageDelay() {
        String fileName = FILE_PREFIX + "1989.csv";
        assertEquals(37, delayAnalyzer.getAverageDelay(fileName, "HNL"), 0);
        assertEquals(30.67, delayAnalyzer.getAverageDelay(fileName, "LAX"), 0);
    }

}
