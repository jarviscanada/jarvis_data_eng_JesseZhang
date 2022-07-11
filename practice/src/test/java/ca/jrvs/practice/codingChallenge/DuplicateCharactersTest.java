package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuplicateCharactersTest {

  @Test
  public void duplicateCharacters() {
    DuplicateCharacters dup = new DuplicateCharacters();
    assertArrayEquals(new String[]{"a", "c"}, dup.duplicateCharacters("A black cat"));
  }
}