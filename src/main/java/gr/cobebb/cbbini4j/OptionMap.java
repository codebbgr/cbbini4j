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

public interface OptionMap extends MultiMap<String, String>, CommentedMap<String, String>
{
    <T> T getAll(Object key, Class<T> clazz);

    void add(String key, Object value);

    void add(String key, Object value, int index);

    <T> T as(Class<T> clazz);

    <T> T as(Class<T> clazz, String keyPrefix);

    String fetch(Object key);

    String fetch(Object key, String defaultValue);

    String fetch(Object key, int index);

    <T> T fetch(Object key, Class<T> clazz);

    <T> T fetch(Object key, Class<T> clazz, T defaultValue);

    <T> T fetch(Object key, int index, Class<T> clazz);

    <T> T fetchAll(Object key, Class<T> clazz);

    void from(Object bean);

    void from(Object bean, String keyPrefix);

    String get(Object key, String defaultValue);

    <T> T get(Object key, Class<T> clazz);

    <T> T get(Object key, Class<T> clazz, T defaultValue);

    <T> T get(Object key, int index, Class<T> clazz);

    String put(String key, Object value);

    String put(String key, Object value, int index);

    void putAll(String key, Object value);

    void to(Object bean);

    void to(Object bean, String keyPrefix);
}
