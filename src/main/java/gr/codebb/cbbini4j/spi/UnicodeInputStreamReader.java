/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.codebb.cbbini4j.spi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.charset.Charset;

class UnicodeInputStreamReader extends Reader {
  private static final int BOM_SIZE = 4;

  private static enum Bom {
    UTF32BE("UTF-32BE", new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF}),
    UTF32LE("UTF-32LE", new byte[] {(byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00}),
    UTF16BE("UTF-16BE", new byte[] {(byte) 0xFE, (byte) 0xFF}),
    UTF16LE("UTF-16LE", new byte[] {(byte) 0xFF, (byte) 0xFE}),
    UTF8("UTF-8", new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
    private final byte[] _bytes;
    private Charset _charset;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    private Bom(String charsetName, byte[] bytes) {
      try {
        _charset = Charset.forName(charsetName);
      } catch (Exception x) {
        _charset = null;
      }

      _bytes = bytes;
    }

    private static Bom find(byte[] data) {
      Bom ret = null;

      for (Bom bom : values()) {
        if (bom.supported() && bom.match(data)) {
          ret = bom;

          break;
        }
      }

      return ret;
    }

    private boolean match(byte[] data) {
      boolean ok = true;

      for (int i = 0; i < _bytes.length; i++) {
        if (data[i] != _bytes[i]) {
          ok = false;

          break;
        }
      }

      return ok;
    }

    private boolean supported() {
      return _charset != null;
    }
  }

  private final Charset _defaultEncoding;
  private InputStreamReader _reader;
  private final PushbackInputStream _stream;

  UnicodeInputStreamReader(InputStream in, Charset defaultEnc) {
    _stream = new PushbackInputStream(in, BOM_SIZE);
    _defaultEncoding = defaultEnc;
  }

  public void close() throws IOException {
    init();
    _reader.close();
  }

  public int read(char[] cbuf, int off, int len) throws IOException {
    init();

    return _reader.read(cbuf, off, len);
  }

  /**
   * Read-ahead four bytes and check for BOM marks. Extra bytes are unread back to the stream, only
   * BOM bytes are skipped.
   */
  protected void init() throws IOException {
    if (_reader != null) {
      return;
    }

    Charset encoding;
    byte[] data = new byte[BOM_SIZE];
    int n;
    int unread;

    n = _stream.read(data, 0, data.length);
    Bom bom = Bom.find(data);

    if (bom == null) {
      encoding = _defaultEncoding;
      unread = n;
    } else {
      encoding = bom._charset;
      unread = data.length - bom._bytes.length;
    }

    if (unread > 0) {
      _stream.unread(data, (n - unread), unread);
    }

    _reader = new InputStreamReader(_stream, encoding);
  }
}
