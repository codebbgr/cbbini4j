/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.codebb.cbbini4j.spi;

public interface BeanAccess {
  void propAdd(String propertyName, String value);

  String propDel(String propertyName);

  String propGet(String propertyName);

  String propGet(String propertyName, int index);

  int propLength(String propertyName);

  String propSet(String propertyName, String value);

  String propSet(String propertyName, String value, int index);
}
