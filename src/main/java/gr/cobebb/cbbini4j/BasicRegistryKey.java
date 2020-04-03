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

import gr.cobebb.cbbini4j.Registry.Key;

class BasicRegistryKey extends BasicProfileSection implements Registry.Key
{
    private static final long serialVersionUID = -1390060044244350928L;
    private static final String META_TYPE = "type";

    public BasicRegistryKey(BasicRegistry registry, String name)
    {
        super(registry, name);
    }

    @Override public Key getChild(String key)
    {
        return (Key) super.getChild(key);
    }

    @Override public Key getParent()
    {
        return (Key) super.getParent();
    }

    @Override public Registry.Type getType(Object key)
    {
        return (Registry.Type) getMeta(META_TYPE, key);
    }

    @Override public Registry.Type getType(Object key, Registry.Type defaultType)
    {
        Registry.Type type = getType(key);

        return (type == null) ? defaultType : type;
    }

    @Override public Key addChild(String key)
    {
        return (Key) super.addChild(key);
    }

    @Override public Key lookup(String... path)
    {
        return (Key) super.lookup(path);
    }

    @Override public Registry.Type putType(String key, Registry.Type type)
    {
        return (Registry.Type) putMeta(META_TYPE, key, type);
    }

    @Override public Registry.Type removeType(Object key)
    {
        return (Registry.Type) removeMeta(META_TYPE, key);
    }
}
