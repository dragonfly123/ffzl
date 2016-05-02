package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 */
public abstract class ResourceLoaderFactory {
    private static Map<LoaderType,ResourceLoader> loaderMap =Maps.newHashMap();
    public static class LoaderType{
        private String filtType;
        private String resourceType;

        public LoaderType(String filtType, String resourceType) {
            this.filtType = filtType;
            this.resourceType = resourceType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LoaderType that = (LoaderType) o;

            if (!filtType.equals(that.filtType)) return false;
            return resourceType.equals(that.resourceType);

        }

        @Override
        public int hashCode() {
            int result = filtType.hashCode();
            result = 31 * result + resourceType.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "LoaderType{" +
                    "filtType='" + filtType + '\'' +
                    ", resourceType='" + resourceType + '\'' +
                    '}';
        }
    }

    public static ResourceLoader getResourceLoader(String fileType,String resourceType){
        FileParse fileParse = null;
        if ("json".equals(fileType)) {
            fileParse = new JsonFileParse();
        } else if ("xml".equals(fileType)) {
            fileParse = new XmlFileParse();
        } else {
            throw new IllegalArgumentException("不支持的文件类型:" + fileType);
        }

        if ("serviceinterface".equals(resourceType)) {
            fileParse = new ServiceInterfaceParse(fileParse);
        } else if ("sql".equals(resourceType)) {
            fileParse = new SqlResourceParse(fileParse);
        } else {
            throw new IllegalArgumentException("不支持的文件名：" + resourceType);
        }
        return ResourceLoader.getInstance(resourceType,fileParse);
    }
}
