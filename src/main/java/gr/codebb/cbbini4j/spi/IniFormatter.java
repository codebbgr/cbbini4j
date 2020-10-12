/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.codebb.cbbini4j.spi;

import gr.cobebb.cbbini4j.Config;
import java.io.PrintWriter;
import java.io.Writer;

public class IniFormatter extends AbstractFormatter implements IniHandler {
  public static IniFormatter newInstance(Writer out, Config config) {
    IniFormatter instance = newInstance();

    instance.setOutput((out instanceof PrintWriter) ? (PrintWriter) out : new PrintWriter(out));
    instance.setConfig(config);

    return instance;
  }

  @Override
  public void endIni() {
    getOutput().flush();
  }

  @Override
  public void endSection() {
    getOutput().print(getConfig().getLineSeparator());
  }

  @Override
  public void startIni() {
    assert true;
  }

  @Override
  public void startSection(String sectionName) {
    setHeader(false);
    if (!getConfig().isGlobalSection() || !sectionName.equals(getConfig().getGlobalSectionName())) {
      getOutput().print(IniParser.SECTION_BEGIN);
      getOutput().print(escapeKey(sectionName));
      getOutput().print(IniParser.SECTION_END);
      getOutput().print(getConfig().getLineSeparator());
    }
  }

  private static IniFormatter newInstance() {
    return ServiceFinder.findService(IniFormatter.class);
  }
}
