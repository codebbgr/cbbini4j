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

import java.io.PrintWriter;

abstract class AbstractFormatter implements HandlerBase
{
    private static final char OPERATOR = '=';
    private static final char COMMENT = '#';
    private static final char SPACE = ' ';
    private Config _config = Config.getGlobal();
    private boolean _header = true;
    private PrintWriter _output;

    @Override public void handleComment(String comment)
    {
        if (getConfig().isComment() && (!_header || getConfig().isHeaderComment()) && (comment != null) && (comment.length() != 0))
        {
            for (String line : comment.split(getConfig().getLineSeparator()))
            {
                getOutput().print(COMMENT);
                getOutput().print(line);
                getOutput().print(getConfig().getLineSeparator());
            }

            if (_header)
            {
                getOutput().print(getConfig().getLineSeparator());
            }
        }

        _header = false;
    }

    @Override public void handleOption(String optionName, String optionValue)
    {
        if (getConfig().isStrictOperator())
        {
            if (getConfig().isEmptyOption() || (optionValue != null))
            {
                getOutput().print(escapeKey(optionName));
                getOutput().print(OPERATOR);
            }

            if (optionValue != null)
            {
                getOutput().print(escapeValue(optionValue));
            }

            if (getConfig().isEmptyOption() || (optionValue != null))
            {
                getOutput().print(getConfig().getLineSeparator());
            }
        }
        else
        {
            String value = ((optionValue == null) && getConfig().isEmptyOption()) ? "" : optionValue;

            if (value != null)
            {
                getOutput().print(escapeKey(optionName));
                getOutput().print(SPACE);
                getOutput().print(OPERATOR);
                getOutput().print(SPACE);
                getOutput().print(escapeValue(value));
                getOutput().print(getConfig().getLineSeparator());
            }
        }

        setHeader(false);
    }

    protected Config getConfig()
    {
        return _config;
    }

    protected void setConfig(Config value)
    {
        _config = value;
    }

    protected PrintWriter getOutput()
    {
        return _output;
    }

    protected void setOutput(PrintWriter value)
    {
        _output = value;
    }

    void setHeader(boolean value)
    {
        _header = value;
    }

    String escapeKey(String input)
    {
        return getConfig().isEscape() ? EscapeTool.getInstance().escape(input) : input;
    }

    String escapeValue(String input)
    {
        return getConfig().isEscape() &&  ! getConfig().isEscapeKeyOnly() ? EscapeTool.getInstance().escape(input) : input;
    }
}
