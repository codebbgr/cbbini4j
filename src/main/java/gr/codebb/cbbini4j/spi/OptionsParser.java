/*
 * Copyright 2013-2020
 * codebb.gr
 */
/**
 * Changelog
 * =========
 * 03/04/2020 (gmoralis) - Initial commit from ini4j project
 */
package gr.codebb.cbbini4j.spi;

import gr.cobebb.cbbini4j.Config;
import gr.cobebb.cbbini4j.InvalidFileFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import java.net.URL;

public class OptionsParser extends AbstractParser
{
    private static final String COMMENTS = "!#";
    private static final String OPERATORS = ":=";

    public OptionsParser()
    {
        super(OPERATORS, COMMENTS);
    }

    public static OptionsParser newInstance()
    {
        return ServiceFinder.findService(OptionsParser.class);
    }

    public static OptionsParser newInstance(Config config)
    {
        OptionsParser instance = newInstance();

        instance.setConfig(config);

        return instance;
    }

    public void parse(InputStream input, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(Reader input, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(URL input, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    private void parse(IniSource source, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        handler.startOptions();
        for (String line = source.readLine(); line != null; line = source.readLine())
        {
            parseOptionLine(line, handler, source.getLineNumber());
        }

        handler.endOptions();
    }
}
