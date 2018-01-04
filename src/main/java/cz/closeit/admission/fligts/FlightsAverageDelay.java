package cz.closeit.admission.fligts;

public class FlightsAverageDelay {

    /**
     * todo: retrieve the helper classes using some DI tool
     * @param args String[]
     */
    public static void main(String[] args) {
        FileHelper helper = new FileHelper(new FileDownloader(), new FileDecompressor());
        String loadedFile = helper.loadFile("1987", "src/main/resources/");
        System.out.println(loadedFile);

        if (loadedFile == null || "".equals(loadedFile)) {
            return;
        }

        String decompressedFile = helper.decompressFile(loadedFile);
        System.out.println(decompressedFile);
    }

}
