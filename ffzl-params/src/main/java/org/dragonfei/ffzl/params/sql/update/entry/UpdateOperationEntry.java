package org.dragonfei.ffzl.params.sql.update.entry;

import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.update.operation.FfzlBkQuery;
import org.dragonfei.ffzl.params.sql.update.operation.FfzlInsert;
import org.dragonfei.ffzl.params.sql.update.operation.FfzlModify;

/**
 * Created by longfei on 16-6-19.
 */
public class UpdateOperationEntry implements OperationEntry {
    public FfzlBkQuery ffzlBkQuery;
    public FfzlInsert ffzlInsert;
    public FfzlModify ffzlModify;
}

