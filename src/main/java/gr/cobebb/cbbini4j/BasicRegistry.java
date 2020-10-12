/*
 * Copyright 2013-2020
 * codebb.gr
 */
/**
 * Changelog ========= 04/04/2020 (gmoralis) - Change "BasicProfile" members visibility
 * correspondingly 03/04/2020 (gmoralis) - Initial commit from ini4j project
 */
package gr.cobebb.cbbini4j;

import gr.cobebb.cbbini4j.Profile.Section;
import gr.codebb.cbbini4j.spi.IniHandler;
import gr.codebb.cbbini4j.spi.RegEscapeTool;
import gr.codebb.cbbini4j.spi.TypeValuesPair;

public class BasicRegistry extends BasicProfile implements Registry {

  private static final long serialVersionUID = -6432826330714504802L;
  private String _version;

  public BasicRegistry() {
    _version = VERSION;
  }

  @Override
  public String getVersion() {
    return _version;
  }

  @Override
  public void setVersion(String value) {
    _version = value;
  }

  @Override
  public Key add(String name) {
    return (Key) super.add(name);
  }

  @Override
  public Key get(Object key) {
    return (Key) super.get(key);
  }

  @Override
  public Key get(Object key, int index) {
    return (Key) super.get(key, index);
  }

  @Override
  public Key put(String key, Section value) {
    return (Key) super.put(key, value);
  }

  @Override
  public Key put(String key, Section value, int index) {
    return (Key) super.put(key, value, index);
  }

  @Override
  public Key remove(Section section) {
    return (Key) super.remove(section);
  }

  @Override
  public Key remove(Object key) {
    return (Key) super.remove(key);
  }

  @Override
  public Key remove(Object key, int index) {
    return (Key) super.remove(key, index);
  }

  @Override
  Key newSection(String name) {
    return new BasicRegistryKey(this, name);
  }

  @Override
  protected void store(IniHandler formatter, Section section, String option) {
    store(formatter, section.getComment(option));
    Type type = ((Key) section).getType(option, Type.REG_SZ);
    String rawName =
        option.equals(Key.DEFAULT_NAME) ? option : RegEscapeTool.getInstance().quote(option);
    String[] values = new String[section.length(option)];

    for (int i = 0; i < values.length; i++) {
      values[i] = section.get(option, i);
    }

    String rawValue = RegEscapeTool.getInstance().encode(new TypeValuesPair(type, values));

    formatter.handleOption(rawName, rawValue);
  }
}
