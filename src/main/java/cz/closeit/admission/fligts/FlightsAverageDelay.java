package cz.closeit.admission.fligts;

public class FlightsAverageDelay {

    /**
     * todo: retrieve the helper classes using some DI tool
     * @param args String[]
     */
    public static void main(String[] args) {
        FileHelper helper = new FileHelper(new FileDownloader());
        System.out.println(helper.loadFile("1987", "src/main/resources/"));
    }

}
