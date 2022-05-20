package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImpl implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Use default logger config
    BasicConfigurator.configure();

    JavaGrepImpl javaGrepImpl = new JavaGrepImpl();
    javaGrepImpl.setRegex(args[0]);
    javaGrepImpl.setRootPath(args[1]);
    javaGrepImpl.setOutFile(args[2]);

    try {
      javaGrepImpl.process();
    } catch (Exception ex) {
      javaGrepImpl.logger.error("Error: Unable to process", ex);
    }
  }


  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    listFiles(rootPath).forEach(file -> {
      readLines(file).forEach(line -> {
            if (containsPattern(line)) {
              matchedLines.add(line);
            }
          }
      );
    });

    writeToFile(matchedLines.stream());
  }

  @Override
  public Stream<File> listFiles(String rootDir) {

    try {
      Stream<Path> stream = Files.walk(Paths.get(rootDir));
      return stream
          .filter(Files::isRegularFile)
          .map(Path::toFile);
    } catch (IOException ex) {
      logger.error("Error: Unable to open the file", ex);
    }

    return null;
  }

  @Override
  public Stream<String> readLines(File inputFile) throws IllegalArgumentException {

    List<String> linesRead = new ArrayList<>();
    try (
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
      String line = bufferedReader.readLine();
      while (line != null) {
        linesRead.add(line);
        line = bufferedReader.readLine();
      }
    } catch (FileNotFoundException ex) {
      logger.error("Error: the input file is not found");
      throw new IllegalArgumentException("The input file is not a file");
    } catch (IOException ex) {
      logger.error("Error: Unable to read a line", ex);
    }

    return linesRead.stream();
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.matches(regex, line);
  }

  @Override
  public void writeToFile(Stream<String> lines) throws IOException {

    try (BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(outFile)))) {
      for (String line : lines.collect(Collectors.toList())) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException ex) {
      logger.error("Error: something wrong with writing to the file");
      throw new IOException("Writing to the file fails");
    }
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
