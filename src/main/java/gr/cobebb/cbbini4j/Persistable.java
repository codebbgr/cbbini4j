/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.cobebb.cbbini4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

public interface Persistable {
  File getFile();

  void setFile(File value);

  void load() throws IOException, InvalidFileFormatException;

  void load(InputStream input) throws IOException, InvalidFileFormatException;

  void load(Reader input) throws IOException, InvalidFileFormatException;

  void load(File input) throws IOException, InvalidFileFormatException;

  void load(URL input) throws IOException, InvalidFileFormatException;

  void store() throws IOException;

  void store(OutputStream output) throws IOException;

  void store(Writer output) throws IOException;

  void store(File output) throws IOException;
}
