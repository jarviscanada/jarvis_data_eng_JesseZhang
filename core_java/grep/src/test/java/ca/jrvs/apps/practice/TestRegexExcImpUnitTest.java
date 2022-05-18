package ca.jrvs.apps.practice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestRegexExcImpUnitTest {

  private RegexExc regexExc = new RegexExcImp();

  @Test
  public void givenFilename_withJPGOrJPEG_thenCorrect() {
    assertTrue(regexExc.matchJpeg("test.jpg"));
    assertTrue(regexExc.matchJpeg("test.JPG"));
    assertTrue(regexExc.matchJpeg("test.jPG"));
    assertTrue(regexExc.matchJpeg("test.jpeg"));
    assertTrue(regexExc.matchJpeg("test.jpEg"));
  }


  @Test
  public void givenFilename_withInvalid_thenWrong() {
    assertFalse(regexExc.matchJpeg("test*.jpeg"));
    assertFalse(regexExc.matchJpeg("t.est.jpeg"));
    assertFalse(regexExc.matchJpeg("test.jpegs"));
  }

  @Test
  public void givenIP_withValid_thenCorrect() {
    assertTrue(regexExc.matchIp("0.0.0.0"));
    assertTrue(regexExc.matchIp("999.999.999.999"));
  }

  @Test
  public void givenIP_withInvalid_thenWrong() {
    assertFalse(regexExc.matchIp("0.0.0.0.0"));
    assertFalse(regexExc.matchIp("1099.999.999.999"));
  }

  @Test
  public void givenEmptyLine_thenCorrect() {
    assertTrue(regexExc.isEmptyLine(""));
    assertTrue(regexExc.isEmptyLine(" "));
    assertTrue(regexExc.isEmptyLine("   "));
  }

  @Test
  public void givenNonEmptyLine_thenWrong() {
    assertFalse(regexExc.isEmptyLine("."));
    assertFalse(regexExc.isEmptyLine("\\n\\n"));
    assertFalse(regexExc.isEmptyLine("\\n"));
    assertFalse(regexExc.isEmptyLine("d " +
        "" +
        ""));
  }
}
