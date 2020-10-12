/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.codebb.cbbini4j.spi;

import gr.cobebb.cbbini4j.Config;
import java.io.PrintWriter;
import java.io.Writer;

public class OptionsFormatter extends AbstractFormatter implements OptionsHandler {
  public static OptionsFormatter newInstance(Writer out, Config config) {
    OptionsFormatter instance = newInstance();

    instance.setOutput((out instanceof PrintWriter) ? (PrintWriter) out : new PrintWriter(out));
    instance.setConfig(config);

    return instance;
  }

  public void endOptions() {
    getOutput().flush();
  }

  public void startOptions() {
    assert true;
  }

  private static OptionsFormatter newInstance() {
    return ServiceFinder.findService(OptionsFormatter.class);
  }
}
