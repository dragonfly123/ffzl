package org.dragonfei.common;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-10.
 */
public interface CommonService {
    <T> void insert(T object);
    <T>List<Map<String,String>> select(T object);
}
