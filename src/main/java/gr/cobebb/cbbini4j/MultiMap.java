/*
 * Copyright 2013-2020
 * codebb.gr
 */
/** Changelog ========= 03/04/2020 (gmoralis) - Initial commit from ini4j project */
package gr.cobebb.cbbini4j;

import java.util.List;
import java.util.Map;

public interface MultiMap<K, V> extends Map<K, V> {
  List<V> getAll(Object key);

  void add(K key, V value);

  void add(K key, V value, int index);

  V get(Object key, int index);

  int length(Object key);

  V put(K key, V value, int index);

  List<V> putAll(K key, List<V> values);

  V remove(Object key, int index);
}
