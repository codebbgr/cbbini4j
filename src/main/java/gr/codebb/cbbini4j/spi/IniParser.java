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

import java.util.Locale;

public class IniParser extends AbstractParser
{
    private static final String COMMENTS = ";#";
    private static final String OPERATORS = ":=";
    static final char SECTION_BEGIN = '[';
    static final char SECTION_END = ']';

    public IniParser()
    {
        super(OPERATORS, COMMENTS);
    }

    public static IniParser newInstance()
    {
        return ServiceFinder.findService(IniParser.class);
    }

    public static IniParser newInstance(Config config)
    {
        IniParser instance = newInstance();

        instance.setConfig(config);

        return instance;
    }

    public void parse(InputStream input, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(Reader input, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(URL input, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    private void parse(IniSource source, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        handler.startIni();
        String sectionName = null;

        for (String line = source.readLine(); line != null; line = source.readLine())
        {
            if (line.charAt(0) == SECTION_BEGIN)
            {
                if (sectionName != null)
                {
                    handler.endSection();
                }

                sectionName = parseSectionLine(line, source, handler);
            }
            else
            {
                if (sectionName == null)
                {
                    if (getConfig().isGlobalSection())
                    {
                        sectionName = getConfig().getGlobalSectionName();
                        handler.startSection(sectionName);
                    }
                    else
                    {
                        parseError(line, source.getLineNumber());
                    }
                }

                parseOptionLine(line, handler, source.getLineNumber());
            }
        }

        if (sectionName != null)
        {
            handler.endSection();
        }

        handler.endIni();
    }

    private String parseSectionLine(String line, IniSource source, IniHandler handler) throws InvalidFileFormatException
    {
        String sectionName;

        if (line.charAt(line.length() - 1) != SECTION_END)
        {
            parseError(line, source.getLineNumber());
        }

        sectionName = unescapeKey(line.substring(1, line.length() - 1).trim());
        if ((sectionName.length() == 0) && !getConfig().isUnnamedSection())
        {
            parseError(line, source.getLineNumber());
        }

        if (getConfig().isLowerCaseSection())
        {
            sectionName = sectionName.toLowerCase(Locale.getDefault());
        }

        handler.startSection(sectionName);

        return sectionName;
    }
}
