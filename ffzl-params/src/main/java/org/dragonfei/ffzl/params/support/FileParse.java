package org.dragonfei.ffzl.params.support;

/**
 * Created by longfei on 16-4-25.
 */
public interface FileParse {
    <T,E> T parse(E object,String namespace);
    <E> boolean supported(E object);
}
