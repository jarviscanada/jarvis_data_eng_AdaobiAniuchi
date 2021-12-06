package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the JavaGrepLambdaAndStream interface that uses the Stream API and Lambda.
 */
public class JavaGrepLambdaAndStreamImp implements JavaGrepLambdaAndStream {
    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public void process() {
        Stream<File> fileStream = listFiles(getRootPath());
        Stream<Stream<String>> stringStream = fileStream.map(this::readLines);
        stringStream.forEach(stringStream1 -> {
            try {
                writeToFile(stringStream1);
            } catch (IOException ex) {
                logger.error("Unable to write to file. The following error occurred", ex);
            }
        });
    }

    @Override
    public Stream<File> listFiles(String rootDir) {
        try (Stream<Path> walk = Files.walk(Paths.get(rootDir))) {
            return walk.filter(Files::isRegularFile).map(Path::toFile);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Directory does not exist", ex);
        }
    }

    @Override
    public Stream<String> readLines(File inputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            return reader.lines();

        } catch (IOException ex) {
            throw new IllegalArgumentException("File could not be read", ex);
        }
    }

    @Override
    public boolean containsPattern(String line) {
        return Pattern.matches(getRegex(), line);
    }

    @Override
    public void writeToFile(Stream<String> lines) throws IOException {
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(getOutFile())
                )
        );

        lines.forEach(line -> {
            try {
                writer.write(line + "\n");
            } catch (IOException ex) {
                logger.error("Unable to write to file", ex);
            }
        });
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
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
