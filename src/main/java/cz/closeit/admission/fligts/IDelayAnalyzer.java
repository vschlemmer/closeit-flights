package cz.closeit.admission.fligts;

public interface IDelayAnalyzer {

    /**
     *
     * Analyzes csv file and returns average delay of uncancelled flights coming to the given airport
     * The returned value is rounded to 2 decimal places
     * @param filePath String
     * @param airportCode String
     * @return double
     */
    double getAverageDelay(String filePath, String airportCode);

}
