/*
 * Copyright 2013-2020
 * codebb.gr
 */
/**
 * Changelog
 * =========
 * 03/04/2020 (gmoralis) - renamed remove(obj,obj) function to removeObj(obj,obj)
 * 03/04/2020 (gmoralis) - Initial commit from ini4j project
 */
package gr.cobebb.cbbini4j;

public interface Profile extends MultiMap<String, Profile.Section>, CommentedMap<String, Profile.Section>
{
    char PATH_SEPARATOR = '/';

    String getComment();

    void setComment(String value);

    Section add(String sectionName);

    void add(String sectionName, String optionName, Object value);

    <T> T as(Class<T> clazz);

    <T> T as(Class<T> clazz, String prefix);

    String fetch(Object sectionName, Object optionName);

    <T> T fetch(Object sectionName, Object optionName, Class<T> clazz);

    String get(Object sectionName, Object optionName);

    <T> T get(Object sectionName, Object optionName, Class<T> clazz);

    String put(String sectionName, String optionName, Object value);

    Section remove(Profile.Section section);

    String removeObj(Object sectionName, Object optionName);

    interface Section extends OptionMap
    {
        Section getChild(String key);

        String getName();

        Section getParent();

        String getSimpleName();

        Section addChild(String key);

        String[] childrenNames();

        Section lookup(String... path);

        void removeChild(String key);
    }
}
