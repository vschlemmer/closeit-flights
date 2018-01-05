package cz.closeit.admission.fligts;

public class FlightsAverageDelay {

    /**
     * mvn exec:java -Dexec.args="1989 LAX"
     * @param args String[]
     */
    public static void main(String[] args) {
        if (!Util.areArgumentsValid(args)) {
            return;
        }

        String year = args[0];
        String airport = args[1].toUpperCase();

        // todo: manage the helper classes using DI
        FileHelper helper = new FileHelper(new FileDownloader(), new FileDecompressor());

        // load the file
        String loadedFile = helper.loadFile(year, "src/main/resources/");
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
        System.out.println(
            "Average delay of flights to " + airport + ": " +
            delayAnalyzer.getAverageDelay(decompressedFile, airport) + " minutes."
        );
    }

}
