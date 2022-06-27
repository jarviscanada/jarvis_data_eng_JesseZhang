package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImpl extends JavaGrepImpl {

  BufferedReader bufferedReader;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Use default logger config
    BasicConfigurator.configure();

    JavaGrepLambdaImpl javaGrepLambdaImpl = new JavaGrepLambdaImpl();
    javaGrepLambdaImpl.setRegex(args[0]);
    javaGrepLambdaImpl.setRootPath(args[1]);
    javaGrepLambdaImpl.setOutFile(args[2]);

    try {
      javaGrepLambdaImpl.process();
    } catch (Exception ex) {
      javaGrepLambdaImpl.logger.error("Error: Unable to process", ex);
    } finally {
      javaGrepLambdaImpl.cleanup();
    }
  }

  private void cleanup() {
    try {
      bufferedReader.close();
    } catch (IOException e) {
      logger.error("Error: when closing the bufferedReader");
    }
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
    Stream<String> linesRead = Stream.empty();
    try {
      bufferedReader = new BufferedReader(new FileReader(inputFile));
      linesRead = bufferedReader.lines();
    } catch (FileNotFoundException ex) {
      logger.error("Error: the input file is not found");
      throw new IllegalArgumentException("The input file is not a file");
    }

    return linesRead;
  }
}
