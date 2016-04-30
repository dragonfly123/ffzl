package org.dragonfei.ffzl.params.resource;

/**
 * Created by longfei on 16-4-25.
 */
public interface FileParse {
    <T,E> T parse(E object);
    <E> boolean supported(E object);
}
