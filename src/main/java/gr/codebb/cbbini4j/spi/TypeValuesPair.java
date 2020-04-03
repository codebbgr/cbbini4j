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

import gr.cobebb.cbbini4j.Registry.Type;

public class TypeValuesPair
{
    private final Type _type;
    private final String[] _values;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    public TypeValuesPair(Type type, String[] values)
    {
        _type = type;
        _values = values;
    }

    public Type getType()
    {
        return _type;
    }

    public String[] getValues()
    {
        return _values;
    }
}
