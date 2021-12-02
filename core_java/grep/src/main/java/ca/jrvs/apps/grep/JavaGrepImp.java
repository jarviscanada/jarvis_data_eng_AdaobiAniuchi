package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Implementation for the Java Grep Interface.
 */
public class JavaGrepImp implements JavaGrep {
    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    /**
     * Top level search workflow.
     *
     * @throws IOException exception from accessing files
     */
    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        String rootDir = getRootPath();

        for (File file : listFiles((rootDir))) {
            for (String line : readLines(file)) {
                if(containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);
    }

    /**
     * Traverse a given directory and return all files
     *
     * @param rootDir input directory
     * @return files under the rootDir
     */
    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new ArrayList<>();
        File dir = new File(rootDir);

        File[] dirItems = dir.listFiles();

        if (dirItems != null){
            for(File dirItem : dirItems) {
                if(dirItem.isDirectory()) {
                    files.addAll(listFiles(dirItem.getPath()));
                } else {
                    files.add(dirItem);
                }
            }
        }
        return files;
    }

    /**
     * Read a file and return all the lines
     * <p>
     * Explain FileReader, BufferedReader, and character encoding
     *
     * @param inputFile file to be read
     * @return lines
     * @throws IllegalArgumentException if a given input file is not a file
     */
    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file is not a file", ex);
        } catch (IOException ex) {
            throw new IllegalArgumentException("File could not be read", ex);
        }

        return lines;
    }

    /**
     * Checks if a line contains the regex pattern passed by user
     *
     * @param line input line to be checked
     * @return true if there is a match
     */
    @Override
    public boolean containsPattern(String line) {
        return Pattern.matches(getRegex(), line);
    }

    /**
     * Write lines to a file
     * <p>
     * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
     *
     * @param lines matched lines
     * @throws IOException if write failed
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException {
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(getOutFile())
                )
        );
        for (String line: lines) {
            writer.write(line);
            writer.write("\n");
        }

        writer.close();
    }

    /**
     * Get the root path from arguments provided to the command
     *
     * @return root path argument
     */
    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    /**
     * Set the root path
     */
    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    /**
     * Get the regex provided by user
     *
     * @return the regex expression provided by user
     */
    @Override
    public String getRegex() {
        return this.regex;
    }

    /**
     * Set the regex in the program
     */
    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * Get the output file name to save output to
     *
     * @return the output file name
     */
    @Override
    public String getOutFile() {
        return this.outFile;
    }

    /**
     * Set the output file name in program
     */
    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        BasicConfigurator.configure();

        try {
            javaGrepImp.process();
        } catch (IOException ex) {
            javaGrepImp.logger.error("Error processing directory", ex);
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }
    }
}
