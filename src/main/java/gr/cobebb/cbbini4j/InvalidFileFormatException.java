/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.cobebb.cbbini4j;

import java.io.IOException;

public class InvalidFileFormatException extends IOException {
  private static final long serialVersionUID = -4354616830804732309L;

  public InvalidFileFormatException(String message) {
    super(message);
  }
}
