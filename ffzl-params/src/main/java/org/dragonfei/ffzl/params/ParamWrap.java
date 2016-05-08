package org.dragonfei.ffzl.params;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;


import java.util.Map;

/**
 * Created by longfei on 16-4-23.
 * 参数封装
 */
public class ParamWrap {
    private Map<String,String>  params = Maps.newHashMap();
    private String servicename;
    private String fullservicename;
    private int page = 1;
    private int pageSize  = 10;
    private boolean ignore_page = false;

    public boolean isIgnore_page() {
        return ignore_page;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getServicename() {
        return servicename;
    }

    public String getFullservicename() {
        return fullservicename;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getParam(String key){
        return ObjectUtils.nvl(params.get(key),StringUtils.EMTY);
    }

    public boolean containParam(String key){
        return params.containsKey(key);
    }


    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setServicename(String servicename) {
        this.fullservicename = servicename;
        this.servicename = servicename.substring(servicename.lastIndexOf("_")+1);
    }


    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setIgnore_page(boolean ignore_page) {
        this.ignore_page = ignore_page;
    }


    @Override
    public String toString() {
        return "ParamWrap{" +
                "params=" + params +
                ", servicename='" + servicename + '\'' +
                ", fullservicename='" + fullservicename + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", ignore_page=" + ignore_page +"}";
    }
        public ParamWrap param(String key,String value){
            if("page".equals(key)){
                setPage(Integer.parseInt(value));
            }  else if("pageSize".equals(key)){
                setPageSize(Integer.parseInt(value));
            } else if("servicename".equals(key)){
                setServicename(value);
            }  else if("ignore_page".equals(key)){
                if("1".equals(value)  || "true".equals(value)){
                    setIgnore_page(true);
                }
            } else {
                this.params.put(key, value);
            }
            return this;
        }


}
