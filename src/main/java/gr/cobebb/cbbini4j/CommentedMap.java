/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.cobebb.cbbini4j;

import java.util.Map;

public interface CommentedMap<K, V> extends Map<K, V> {
  String getComment(Object key);

  String putComment(K key, String comment);

  String removeComment(Object key);
}
