/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.codebb.cbbini4j.spi;

interface HandlerBase {
  void handleComment(String comment);

  void handleOption(String optionName, String optionValue);
}
