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

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CommonMultiMap<K, V> extends BasicMultiMap<K, V> implements CommentedMap<K, V>
{
    private static final long serialVersionUID = 3012579878005541746L;
    private static final String SEPARATOR = ";#;";
    private static final String FIRST_CATEGORY = "";
    private static final String LAST_CATEGORY = "zzzzzzzzzzzzzzzzzzzzzz";
    private static final String META_COMMENT = "comment";
    private SortedMap<String, Object> _meta;

    @Override public String getComment(Object key)
    {
        return (String) getMeta(META_COMMENT, key);
    }

    @Override public void clear()
    {
        super.clear();
        if (_meta != null)
        {
            _meta.clear();
        }
    }

    @SuppressWarnings("unchecked")
    @Override public void putAll(Map<? extends K, ? extends V> map)
    {
        super.putAll(map);
        if (map instanceof CommonMultiMap)
        {
            Map<String, String> meta = ((CommonMultiMap) map)._meta;

            if (meta != null)
            {
                meta().putAll(meta);
            }
        }
    }

    @Override public String putComment(K key, String comment)
    {
        return (String) putMeta(META_COMMENT, key, comment);
    }

    @Override public V remove(Object key)
    {
        V ret = super.remove(key);

        removeMeta(key);

        return ret;
    }

    @Override public V remove(Object key, int index)
    {
        V ret = super.remove(key, index);

        if (length(key) == 0)
        {
            removeMeta(key);
        }

        return ret;
    }

    @Override public String removeComment(Object key)
    {
        return (String) removeMeta(META_COMMENT, key);
    }

    Object getMeta(String category, Object key)
    {
        return (_meta == null) ? null : _meta.get(makeKey(category, key));
    }

    Object putMeta(String category, K key, Object value)
    {
        return meta().put(makeKey(category, key), value);
    }

    void removeMeta(Object key)
    {
        if (_meta != null)
        {
            _meta.subMap(makeKey(FIRST_CATEGORY, key), makeKey(LAST_CATEGORY, key)).clear();
        }
    }

    Object removeMeta(String category, Object key)
    {
        return (_meta == null) ? null : _meta.remove(makeKey(category, key));
    }

    private String makeKey(String category, Object key)
    {
        StringBuilder buff = new StringBuilder();

        buff.append(String.valueOf(key));
        buff.append(SEPARATOR);
        buff.append(category);

        return buff.toString();
    }

    private Map<String, Object> meta()
    {
        if (_meta == null)
        {
            _meta = new TreeMap<String, Object>();
        }

        return _meta;
    }
}
