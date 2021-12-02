package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * return true if filename extension is jpg or jpeg (case-insensitive)
     * @param filename Name of the file
     * @return An appropriate boolean if the file name extension is jpg or jpeg or not
     */
    boolean matchJpeg(String filename);

    /**
     * return true if ip is valid
     * IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip IP address string
     * @return An appropriate boolean if ip is a valid IP address or not
     */
    boolean matchIp(String ip);

    /**
     * return true if line is empty (e.g. empty, white space, tabs, etc.)
     * @param line A given line
     * @return An appropriate boolean if the line is empty or not.
     */
    boolean isEmptyLine(String line);
}
