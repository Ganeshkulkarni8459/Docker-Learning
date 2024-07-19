package org.dnyanyog.utilities;

import java.security.SecureRandom;

public class RandomStringGenerator {

  private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";

  public static synchronized String generateRandomString(final int length) { // Multiple copies ???

    SecureRandom random = new SecureRandom();
    char[] randomString = new char[length];
    int charLength = CHARACTERS.length();
    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(charLength);
      randomString[i] = CHARACTERS.charAt(randomIndex);
    }
    return new String(randomString);
  }

  public static void main(String[] args) {}
}
