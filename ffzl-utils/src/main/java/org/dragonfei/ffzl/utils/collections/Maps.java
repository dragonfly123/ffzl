package org.dragonfei.ffzl.utils.collections;

import org.springframework.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 16/4/19.
 */
public abstract class Maps {
    public static<K,V> Map<K,V> newHashMap(){
        return new HashMap<>();
    }

    public static<K,V> Map<K,V> newConcurrentHashMap(){
        return new ConcurrentHashMap<>();
    }

    public static<K,V> Map<K,V> newTreeMap(){
        return new TreeMap<>();
    }

}
