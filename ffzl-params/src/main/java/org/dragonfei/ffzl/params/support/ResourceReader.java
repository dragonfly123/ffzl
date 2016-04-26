package org.dragonfei.ffzl.params.support;

import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
public interface ResourceReader {

    Map<String,Object> loadResources(String path);
    Map<String,Object> loadServices(String servicename);
}
