package cz.closeit.admission.fligts;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DelayAnalyzer implements IDelayAnalyzer {
    private final String SEPARATOR = ",";
    private final String NOT_AVAILABLE = "NA";
    private final String NOT_CANCELLED = "0";

    @Override
    public double getAverageDelay(String filePath, String airportCode) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int flightsCount = 0;
            int delayAgg = 0;
            int linesCount = 0;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                String[] flight = line.split(SEPARATOR);

                // the first line contains field names
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (airportCode.equals(flight[17]) && NOT_CANCELLED.equals(flight[21])) {
                    flightsCount++;
                    if (!NOT_AVAILABLE.equals(flight[14])) {
                        delayAgg += Integer.parseInt(flight[14]);
                    }
                }
                linesCount++;
            }

            System.out.println("Flights processed: " + linesCount);
            double averageDelay = (double)delayAgg / flightsCount;
            return (double)Math.round(averageDelay * 100.0) / 100.0;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
