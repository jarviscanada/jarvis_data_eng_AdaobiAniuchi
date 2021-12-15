# Introduction
The grep project is a Java implementation of the `grep` functionality. It searches for a text pattern recursively in a given directory and outputs the matched lines to a specified file.
There are two approaches taken to providing the stated functionality: `JavaGrepImp` - imperative programming through for loops and `JavaGrepLambdaAndStreamImp` - functional programming using the Stream API.
Both interfaces break down the workflow into bite-sized steps contained in functions.
The grep project is written in core Java using libraries like `java,io`, `sl4j`, `java.nio`, lambda expressions, and the Stream API. Other technologies used are Maven, the IntelliJ IDE and Docker.

# Quick Start
## On the Command Line
- Navigate to the project directory
  ```
  cd core_java/grep
  ```
- Compile and Package the Java Code
  ```
  mvn clean package
  ```
- Launch a JVM and run the app using either of these approaches:
  ```
  java -classpath target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepLambdaAndStreamImp .*Romeo.*Juliet.* ./data /out/grep.txt
  ```
  Or
  ```
  java -jar target/grep-1.0-SNAPSHOT.jar .*Romeo.*Juliet.* ./data /out/grep.txt
  ```
- Run it from a Docker image
  ```
  docker pull adaobia/grep:latest
  docker run --rm -v {host/directory/to/search}:/data -v {host/path/to/store/output/file}:/out adaobi/grep {pattern} /data /out/grep.out.txt
  ```

## In the IDE
Provide the following as command line arguments before running `JavaGrepImp` or `JavaGrepLambdaAndStreamImp` programs in the IDE:
```
.*Romeo.*Juliet.* ./grep/data ./grep/out/grep.txt
```

#Implementation
## Pseudocode
```
matched_lines = []
for file in listFilesRecursively(directory)
  for line in readLines(file)
    if containsPattern(line)
      matchedLines.add(line)
writeToFile(matched_lines)
```

## Performance Issue
In the first Java implementation of the project in `JavaGrepImp`, Lists, Arrays and loops were used to handle file content.
However, this approached is problematic for processing large files when there are memory constraints on the heap size. Java Streams from the Stream API was used as a way to solve the problem in `JavaGrepLambdaAndStreamImp`.

# Test
The project directory contains a `shakespeare.txt` file in the `data/txt` folder. This data is a relatively large text file containing multiple plays. 
Using both the command line and the run configuration settings in the IDE to provide and remove command-line arguments, I ran tests against the data contained in the `data` folder as well as in the entire project folder.
I checked that the right errors were thrown for missing arguments and unavailable directories.
I also compared the results manually to confirm that the lines matched and outputted to the specified file are found in the files used for testing.

# Deployment
The application is first compiled into an UberJar file. Then using a created Dockerfile, a custom Docker image is built from the `openjdk:8-alpine` image using the generated jar file. 
Finally, it is deployed to Docker Hub under the name `grep` for easy distribution.

# Improvement
- Include the line numbers on which each occurrence of the pattern is found.
- Use unit testing for a more thorough testing approach.
- Log the matched lines to the console to provide immediate feedback as the app runs.
