package com.security.utils;

import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

public final class GenerateCodeUtil {

  private GenerateCodeUtil() {}

  public static String generateCode() {
    String code;
    try {
       SecureRandom random = SecureRandom
                      .getInstanceStrong();
       code = String
      .valueOf(random.nextInt(9000) +
                                   1000);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(
            "Problem when generating "+
                      "the random code.");
    }
    return code;

  }


}
