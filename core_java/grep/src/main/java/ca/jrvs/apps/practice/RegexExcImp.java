package ca.jrvs.apps.practice;

import java.util.Scanner;
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
        String regex = "(?i)[a-zA-Z0-9_\\-]+\\.\\bjpe?g\\b";
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

    public static void main(String[] args) {
        System.out.println("Demonstrating usage of the created functions...");
        System.out.println("Enter a file name to check if it is a jpeg or jpg file:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        RegexExcImp regexExcImp = new RegexExcImp();
        boolean isJpeg = regexExcImp.matchJpeg(input);
        while (!isJpeg) {
            System.out.println("File is not a jpg or jpeg file. Try again.");
            input = scanner.next();
            isJpeg = regexExcImp.matchJpeg(input);
        }
        System.out.println("The filename inputted " + input + " has the jpg or jpeg extension.");

        System.out.println("Enter an IP address to see if it is a match");
        input = scanner.next();
        boolean isIp = regexExcImp.matchIp(input);
        while (!isIp) {
            System.out.println(input + " is not a valid IP address. Please try again.");
            input = scanner.next();
            isIp = regexExcImp.matchIp(input);
        }
        System.out.println(input + " is a valid IP address.");

        System.out.println("Enter an empty line using just spaces or the tab character or just press enter");
        input = scanner.nextLine();
        boolean isEmptyLine = regexExcImp.isEmptyLine(input);
        while (!isEmptyLine) {
            System.out.println("The line is not empty. Please try again.");
            input = scanner.nextLine();
            isEmptyLine = regexExcImp.isEmptyLine(input);
        }
        System.out.println("The line you provided is empty.");

        System.out.println("\nThe program has now ended.");
    }
}
