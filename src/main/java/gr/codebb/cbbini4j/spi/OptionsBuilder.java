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
import gr.cobebb.cbbini4j.Options;

public class OptionsBuilder implements OptionsHandler
{
    private boolean _header;
    private String _lastComment;
    private Options _options;

    public static OptionsBuilder newInstance(Options opts)
    {
        OptionsBuilder instance = newInstance();

        instance.setOptions(opts);

        return instance;
    }

    public void setOptions(Options value)
    {
        _options = value;
    }

    @Override public void endOptions()
    {

        // comment only .opt file ...
        if ((_lastComment != null) && _header)
        {
            setHeaderComment();
        }
    }

    @Override public void handleComment(String comment)
    {
        if ((_lastComment != null) && _header)
        {
            setHeaderComment();
            _header = false;
        }

        _lastComment = comment;
    }

    @Override public void handleOption(String name, String value)
    {
        if (getConfig().isMultiOption())
        {
            _options.add(name, value);
        }
        else
        {
            _options.put(name, value);
        }

        if (_lastComment != null)
        {
            if (_header)
            {
                setHeaderComment();
            }
            else
            {
                putComment(name);
            }

            _lastComment = null;
        }

        _header = false;
    }

    @Override public void startOptions()
    {
        if (getConfig().isHeaderComment())
        {
            _header = true;
        }
    }

    protected static OptionsBuilder newInstance()
    {
        return ServiceFinder.findService(OptionsBuilder.class);
    }

    private Config getConfig()
    {
        return _options.getConfig();
    }

    private void setHeaderComment()
    {
        if (getConfig().isComment())
        {
            _options.setComment(_lastComment);
        }
    }

    private void putComment(String key)
    {
        if (getConfig().isComment())
        {
            _options.putComment(key, _lastComment);
        }
    }
}
