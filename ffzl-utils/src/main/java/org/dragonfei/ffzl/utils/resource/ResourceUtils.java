package org.dragonfei.ffzl.utils.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * Created by longfei on 16-4-24.
 */
public abstract class ResourceUtils {
    private static PathMatchingResourcePatternResolver  resourcePatternResolver  = new PathMatchingResourcePatternResolver();

    /**
     * 获取资源
     * @param classpath
     * @return
     * @throws IOException
     */
    public static Resource[] getResource(String classpath) throws IOException{
        return resourcePatternResolver.getResources(ResourceLoader.CLASSPATH_URL_PREFIX+classpath);
    }
}
