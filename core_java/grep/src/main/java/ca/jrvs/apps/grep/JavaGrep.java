package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * Top level search workflow
     * @throws IOException exception from accessing files
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    List<File> listFiles(String rootDir);

    /**
     * Read a file and return all the lines
     *
     * Explain FileReader, BufferedReader, and character encoding
     *
     * @param inputFile file to be read
     * @return lines
     * @throws IllegalArgumentException if a given input file is not a file
     */
    List<String> readLines(File inputFile);

    /**
     * Checks if a line contains the regex pattern passed by user
     * @param line input line to be checked
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     *
     * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
     *
     * @param lines matched lines
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    /**
     * Get the root path from arguments provided to the command
     * @return root path argument
     */
    String getRootPath();

    /**
     * Set the root path
     */
    void setRootPath(String rootPath);

    /**
     * Get the regex provided by user
     * @return the regex expression provided by user
     */
    String getRegex();

    /**
     * Set the regex in the program
     */
    void setRegex(String regex);

    /**
     * Get the output file name to save output to
     * @return the output file name
     */
    String getOutFile();

    /**
     * Set the output file name in program
     */
    void setOutFile(String outFile);
}
