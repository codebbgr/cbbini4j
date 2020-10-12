/*
 * Copyright 2013-2020
 * codebb.gr
 */
/**
 * Changelog ========= 04/04/2020 (gmoralis) - Change "AbstractProfileBuilder" members visibility
 * correspondingly 03/04/2020 (gmoralis) - Initial commit from ini4j project
 */
package gr.codebb.cbbini4j.spi;

import gr.cobebb.cbbini4j.Config;
import gr.cobebb.cbbini4j.Ini;
import gr.cobebb.cbbini4j.Profile;

public class IniBuilder extends AbstractProfileBuilder implements IniHandler {

  private Ini _ini;

  public static IniBuilder newInstance(Ini ini) {
    IniBuilder instance = newInstance();

    instance.setIni(ini);

    return instance;
  }

  public void setIni(Ini value) {
    _ini = value;
  }

  @Override
  protected Config getConfig() {
    return _ini.getConfig();
  }

  @Override
  protected Profile getProfile() {
    return _ini;
  }

  private static IniBuilder newInstance() {
    return ServiceFinder.findService(IniBuilder.class);
  }
}
