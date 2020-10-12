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
import gr.cobebb.cbbini4j.Profile;
import gr.cobebb.cbbini4j.Reg;
import gr.cobebb.cbbini4j.Registry.Key;
import gr.cobebb.cbbini4j.Registry.Type;

public class RegBuilder extends AbstractProfileBuilder {

  private Reg _reg;

  public static RegBuilder newInstance(Reg reg) {
    RegBuilder instance = newInstance();

    instance.setReg(reg);

    return instance;
  }

  public void setReg(Reg value) {
    _reg = value;
  }

  @Override
  public void handleOption(String rawName, String rawValue) {
    String name =
        (rawName.charAt(0) == EscapeTool.DOUBLE_QUOTE)
            ? RegEscapeTool.getInstance().unquote(rawName)
            : rawName;
    TypeValuesPair tv = RegEscapeTool.getInstance().decode(rawValue);

    if (tv.getType() != Type.REG_SZ) {
      ((Key) getCurrentSection()).putType(name, tv.getType());
    }

    for (String value : tv.getValues()) {
      super.handleOption(name, value);
    }
  }

  @Override
  protected Config getConfig() {
    return _reg.getConfig();
  }

  @Override
  protected Profile getProfile() {
    return _reg;
  }

  private static RegBuilder newInstance() {
    return ServiceFinder.findService(RegBuilder.class);
  }
}
