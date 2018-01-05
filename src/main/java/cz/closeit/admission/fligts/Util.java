package cz.closeit.admission.fligts;

public class Util {

    /**
     * Checks if given array of arguments is valid for the application usage
     * @param args String[]
     * @return boolean
     */
    public static boolean areArgumentsValid(String[] args) {
        if (args.length != 2) {
            argumentsInvalid("Expecting 2 arguments: a year and an airport code");
            return false;
        }

        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException|NullPointerException e) {
            argumentsInvalid("The first argument must be a number.");
            return false;
        }

        if ("".equals(args[1])) {
            argumentsInvalid("The second argument must be a non empty string.");
            return false;
        }

        return true;
    }

    /**
     * Gets the percentage progress of the file size
     * @param offset long
     * @param fileSize long
     * @return long
     */
    public static long getProgress(long offset, long fileSize) {
        return (long)((float)offset / fileSize * 100);
    }

    /**
     * Removes the suffix from file path
     * @param filePath String
     * @return String
     */
    public static String getFilePathWithoutSuffix(String filePath) {
        if (filePath.contains(".")) {
            return filePath.substring(0, filePath.lastIndexOf('.'));
        }

        return "";
    }


    private static void argumentsInvalid(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println("Example usage: mvn exec:java -Dexec.args=\"1989 LAX\"");
    }

}
