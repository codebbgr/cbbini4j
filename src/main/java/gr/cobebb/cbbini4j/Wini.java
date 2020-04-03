/*
 * Copyright 2013-2020
 * codebb.gr
 */
/**
 * Changelog
 * =========
 * 03/04/2020 (gmoralis) - Initial commit from ini4j project
 */
package gr.cobebb.cbbini4j;

import gr.codebb.cbbini4j.spi.WinEscapeTool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import java.net.URL;

public class Wini extends Ini
{
    private static final long serialVersionUID = -2781377824232440728L;
    public static final char PATH_SEPARATOR = '\\';

    public Wini()
    {
        Config cfg = Config.getGlobal().clone();

        cfg.setEscape(false);
        cfg.setEscapeNewline(false);
        cfg.setGlobalSection(true);
        cfg.setEmptyOption(true);
        cfg.setMultiOption(false);
        cfg.setPathSeparator(PATH_SEPARATOR);
        setConfig(cfg);
    }

    public Wini(File input) throws IOException, InvalidFileFormatException
    {
        this();
        setFile(input);
        load();
    }

    public Wini(URL input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Wini(InputStream input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Wini(Reader input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public String escape(String value)
    {
        return WinEscapeTool.getInstance().escape(value);
    }

    public String unescape(String value)
    {
        return WinEscapeTool.getInstance().unescape(value);
    }
}
