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

import gr.cobebb.cbbini4j.CommentedMap;
import gr.cobebb.cbbini4j.Config;
import gr.cobebb.cbbini4j.Ini;
import gr.cobebb.cbbini4j.Profile;

abstract class AbstractProfileBuilder implements IniHandler
{
    private Profile.Section _currentSection;
    private boolean _header;
    private String _lastComment;

    @Override public void endIni()
    {

        // comment only .ini files....
        if ((_lastComment != null) && _header)
        {
            setHeaderComment();
        }
    }

    @Override public void endSection()
    {
        _currentSection = null;
    }

    @Override public void handleComment(String comment)
    {
        if ((_lastComment != null) && _header)
        {
            _header = false;
            setHeaderComment();
        }

        _lastComment = comment;
    }

    @Override public void handleOption(String name, String value)
    {
        _header = false;
        if (getConfig().isMultiOption())
        {
            _currentSection.add(name, value);
        }
        else
        {
            _currentSection.put(name, value);
        }

        if (_lastComment != null)
        {
            putComment(_currentSection, name);
            _lastComment = null;
        }
    }

    @Override public void startIni()
    {
        if (getConfig().isHeaderComment())
        {
            _header = true;
        }
    }

    @Override public void startSection(String sectionName)
    {
        if (getConfig().isMultiSection())
        {
            _currentSection = getProfile().add(sectionName);
        }
        else
        {
            Ini.Section s = getProfile().get(sectionName);

            _currentSection = (s == null) ? getProfile().add(sectionName) : s;
        }

        if (_lastComment != null)
        {
            if (_header)
            {
                setHeaderComment();
            }
            else
            {
                putComment(getProfile(), sectionName);
            }

            _lastComment = null;
        }

        _header = false;
    }

    abstract Config getConfig();

    abstract Profile getProfile();

    Profile.Section getCurrentSection()
    {
        return _currentSection;
    }

    private void setHeaderComment()
    {
        if (getConfig().isComment())
        {
            getProfile().setComment(_lastComment);
        }
    }

    private void putComment(CommentedMap<String, ?> map, String key)
    {
        if (getConfig().isComment())
        {
            map.putComment(key, _lastComment);
        }
    }
}
