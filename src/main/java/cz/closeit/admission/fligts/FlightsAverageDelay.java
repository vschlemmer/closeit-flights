package cz.closeit.admission.fligts;

public class FlightsAverageDelay {

    /**
     * todo: retrieve the helper classes using some DI tool
     * @param args String[]
     */
    public static void main(String[] args) {
        FileHelper helper = new FileHelper(new FileDownloader(), new FileDecompressor());

        // load the file
        String loadedFile = helper.loadFile("1987", "src/main/resources/");
        if (loadedFile == null || "".equals(loadedFile)) {
            return;
        }

        // decompress file
        String decompressedFile = helper.decompressFile(loadedFile);
        if (decompressedFile == null || "".equals(decompressedFile)) {
            return;
        }

        // get the average delay of the flights
        IDelayAnalyzer delayAnalyzer = new DelayAnalyzer();
        System.out.println("Average delay of flights to LAX: " + delayAnalyzer.getAverageDelay(decompressedFile, "LAX") + " minutes.");
    }

}
