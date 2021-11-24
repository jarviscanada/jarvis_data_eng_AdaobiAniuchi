package ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {
    /**
     * return true if filename extension is jpg or jpeg (case-insensitive)
     * @param filename Name of the file
     * @return An appropriate boolean if the file name extension is jpg or jpeg or not
     */
    @Override
    public boolean matchJpeg(String filename) {
        String regex = "[a-z\\-]+\\.\\bjpe?g\\b";
        int matches = getMatches(regex, filename);
        return matches != 0;
    }

    /**
     * return true if ip is valid
     * IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip IP address string
     * @return An appropriate boolean if ip is a valid IP address or not
     */
    @Override
    public boolean matchIp(String ip) {
        String regex = "[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+";
        int matches = getMatches(regex, ip);
        return matches != 0;
    }

    /**
     * return true if line is empty (e.g. empty, white space, tabs, etc.)
     * @param line A given line
     * @return An appropriate boolean if the line is empty or not.
     */
    @Override
    public boolean isEmptyLine(String line) {
        String regex = "^$|^\\s+$";
        int matches = getMatches(regex, line);
        return matches != 0;
    }

    /**
     * Get the number of matches of a regular expression found in a given expression
     * @param regex The regular expression to match
     * @param expression The expression to check
     * @return The number of matches found
     */
    private int getMatches(String regex, String expression) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        int matches = 0;

        while (matcher.find()) {
            matches++;
        }

        return matches;
    }
}
