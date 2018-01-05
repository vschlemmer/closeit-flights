package cz.closeit.admission.fligts;

public class Util {

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

}
