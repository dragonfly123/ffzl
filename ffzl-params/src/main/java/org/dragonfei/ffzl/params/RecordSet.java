package org.dragonfei.ffzl.params;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
public class RecordSet {
    private int page;
    private int total;
    private int totalRecords;
    private int pageSize;
    private Map<String,List<Map<String,String>>>  dicts;
    private List<Map<String,String>>  data;

    private int code = -1;
    private String msg;
    private Exception e;

}
