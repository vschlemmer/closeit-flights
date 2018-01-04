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

}
