package cz.closeit.admission.fligts;

public class FlightsAverageDelay {

    public static void main(String[] args) {
        ProcessFileHelper helper = new ProcessFileHelper();
        System.out.println(helper.downloadFile("1989"));
    }

}
