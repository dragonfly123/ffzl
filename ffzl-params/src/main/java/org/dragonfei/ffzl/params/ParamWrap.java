package org.dragonfei.ffzl.params;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringUtils;


import java.util.Map;

/**
 * Created by longfei on 16-4-23.
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

    private  ParamWrap paramWrap;

    public ParamWrap getParamWrap() {
        return paramWrap;
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
        return StringUtils.nvl(params.get(key),StringUtils.EMTY);
    }

    public boolean containParam(String key){
        return params.containsKey(key);
    }
    private ParamWrap(){

    }

    public  static ParamWrap newInstance(){
        return new ParamWrap();
    }

    public  class Builder{
        public Builder(){
          paramWrap = new ParamWrap();
        }

        public Builder page(int page){
            paramWrap.page = page;
            return this;
        }

        public Builder pageSize(int pageSize){
            paramWrap.pageSize  =  pageSize;
            return  this;
        }

        public Builder ignorePage(boolean ignorePage){
            paramWrap.ignore_page = ignorePage;
            return this;
        }

        public Builder param(String key,String value){
            if("page".equals(key)){
                page(Integer.parseInt(value));
            }  else if("pageSize".equals(key)){
                pageSize(Integer.parseInt(value));
            } else if("servicename".equals(key)){
                servicename(value);
            }  else if("ignore_page".equals(key)){
                if("1".equals(value)  || "true".equals(value)){
                    ignorePage(true);
                }
            } else {
                paramWrap.params.put(key, value);
            }
            return this;
        }

        public Builder servicename(String  servicename){
            paramWrap.fullservicename = servicename;
            paramWrap.servicename = servicename.substring(servicename.lastIndexOf("_")+1);
            return this;
        }
        public  Builder param(Map<String,String> param){
            paramWrap.params.putAll(param);
            return this;
        }

        public ParamWrap build(){
            return paramWrap;
        }
    }
}
