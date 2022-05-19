package ca.jrvs.apps.practice;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLambdaStreamExcImplUnitTest {

  private final LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImpl();

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void givenStrings_convertToUpperCase() {
    String[] expected = new String[]{"HELLO", "WORLD"};
    Assert.assertArrayEquals(expected, lambdaStreamExc.toUpperCase("hello", "world").toArray());
  }

  @Test
  public void givenStrings_filterNull() {
  String [] expected = new String[]{"hello"};
      Assert.assertArrayEquals(expected, lambdaStreamExc.filter(lambdaStreamExc.createStrStream("hello", "world"), ".*d.*").toArray());
  }

  @Test
  public void createIntStream() {
  }

  @Test
  public void toList() {
  }

  @Test
  public void testToList() {
  }

  @Test
  public void testCreateIntStream() {
  }

  @Test
  public void squareRootIntStream() {
    IntStream intStream = IntStream.of(4,9,10,11);
    double[]expected = {2.0,3.0,3.1,3.3};
    double[]result= lambdaStreamExc.squareRootIntStream(intStream).toArray();
    Assert.assertArrayEquals(expected, result,0.1);
  }

  @Test
  public void getOdd() {
    IntStream intStream = IntStream.of(4,9,10,11);
    int[]expected = {9,11};
    int []result = lambdaStreamExc.getOdd(intStream).toArray();
    Assert.assertArrayEquals(expected, result);
  }

  @Test
  public void getLambdaPrinter() {

    Consumer<String> printer = lambdaStreamExc.getLambdaPrinter("start>", "<end");
    printer.accept("Message body");
    assertEquals("start>Message body<end\n", outContent.toString());
  }

  @Test
  public void printMessages() {
    String expected = "msg:a!\nmsg:b!\nmsg:c!\n";
    String[] messages = {"a","b", "c"};
    lambdaStreamExc.printMessages(messages, lambdaStreamExc.getLambdaPrinter("msg:", "!"));
    String result = outContent.toString();
    assertEquals(expected, result);
  }

  @Test
  public void printOdd() {
    IntStream intStream = lambdaStreamExc.createIntStream(0,5);
    lambdaStreamExc.printOdd(intStream, lambdaStreamExc.getLambdaPrinter("odd number:", "!"));
    String expected = "odd number:1!\nodd number:3!\nodd number:5!\n";
    String result = outContent.toString();
    assertEquals(expected, result);
  }

  @Test
  public void flatNestedInt() {
     List<Integer> ints_list = IntStream.range(0,5).boxed().collect(Collectors.toList());
     Stream<List<Integer>> ints= Stream.of(ints_list);
     Stream<Integer> expected = Stream.of(0, 1, 4, 9,16);

    Assert.assertArrayEquals(expected.toArray(), lambdaStreamExc.flatNestedInt(ints).toArray());

  }
}