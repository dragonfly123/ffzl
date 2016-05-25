package org.dragonfei.ffzl.params;

import org.dragonfei.ffzl.params.sql.SqlParam;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *Created by longfei on 16-4-24.
 * 结果集包装
 */
public class RecordSet implements Serializable {
    public static final int SUCCE_CODE = 0;
    public static final int FAIL_CODE = -1;
    private int page = 0;
    private int total = 0;
    private int totalRecords  = 0;
    private int pageSize  = 10;
    private Map<String,List<Map<String,String>>>  dicts = Maps.newHashMap();
    private List<Map<String,?>>  data = Lists.newArrayList(30);
    private List<Map<String,String>> columns = Lists.newArrayList();
    private int code = -1;
    private String msg;
    private Exception e;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Map<String, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<String, String>> columns) {
        this.columns = columns;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, List<Map<String, String>>> getDicts() {
        return dicts;
    }

    public void setDicts(Map<String, List<Map<String, String>>> dicts) {
        this.dicts = dicts;
    }

    public List<Map<String, ?>> getData() {
        return data;
    }

    public void setData(List<Map<String, ?>> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

   /* @Override
    public String toString() {
        return "RecordSet{" +
                "page=" + page +
                ", total=" + total +
                ", totalRecords=" + totalRecords +
                ", pageSize=" + pageSize +
                ", dicts=" + dicts +
                ", data=" + data +
                ", columns=" + columns +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", e=" + e +
                '}';
    }*/
}
