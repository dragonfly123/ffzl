package org.dragonfei.ffzl.params.resource;

/**
 * Created by longfei on 16-4-25.
 * 解析配置文件,不同的实现,支持不同的配置文件类型
 */
public interface FileParse {
    /**
     *
     * @param object
     * @param <T>
     * @param <E>
     * @return
     * 只有@see #supported(Object) 返回值为true 才会执行此解析方法
     */
    <T,E> T parse(E object);

    /**
     *
     * @param object
     * @param <E>
     * @return
     *
     */
    <E> boolean supported(E object);
}
