package org.dragonfei.ffzl.params.sql.update.operation;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.service.UpdateDataService;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.params.sql.update.entry.UpdateOperationEntry;
import org.dragonfei.ffzl.params.sql.update.entry.UpdateSqlEntry;
import org.dragonfei.ffzl.params.transaction.TransactionFactory;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16/6/20.
 */
public class UpdateSqlOperation implements UpdateDataService,SqlOperation {

    private UpdateOperationEntry operationEntry;
    private UpdateSqlEntry sqlEntry;

    public UpdateSqlOperation(UpdateOperationEntry operationEntry,UpdateSqlEntry sqlEntry){
        this.operationEntry = operationEntry;
        this.sqlEntry = sqlEntry;
    }

    @Override
    public List<Map<String, ?>> queryByBk(ParamWrap pw) {
        final List<String> values = Lists.newArrayList();
        this.sqlEntry.bkList.forEach(bk->{
            if(!pw.containParam(bk)){
                throw new RuntimeException("参数错误");
            } else{
                values.add(pw.getParam(bk));
            }
        });
        return this.operationEntry.ffzlBkQuery.execute(values.toArray());
    }

    @Override
    public Long executeInsert(ParamWrap pw) {
        return TransactionFactory.getInstance().execute(status -> {
            if(queryByBk(pw).size() > 0){
                throw new RuntimeException("业务主键已存在");
            }
            final List<String> values = Lists.newArrayList();
            this.sqlEntry.columnList.forEach(name->{
                if(!pw.containParam(name)){
                    throw new RuntimeException("参数错误");
                } else{
                    values.add(pw.getParam(name));
                }
            });
            int count = this.operationEntry.ffzlInsert.update(values.toArray());
            if(count > 0){
                return this.operationEntry.ffzlInsert.getLastInsert();
            }
            return 0l;
        });
    }

    @Override
    public Long executeModify(ParamWrap pw) {
        return TransactionFactory.getInstance().execute(status -> {
            List<Map<String,?>> bks = queryByBk(pw);
            if((bks.size() ==  0) ||
                    (bks.size() == 1 && StringUtils.equals(String.valueOf(bks.get(0).get(sqlEntry.pk)),pw.getParam(sqlEntry.pk)))) {
                final List<String> values = Lists.newArrayList();
                this.sqlEntry.columnList.forEach(name -> {
                    if (!pw.containParam(name)) {
                        throw new RuntimeException("参数错误");
                    } else {
                        values.add(pw.getParam(name));
                    }
                });
                values.add(pw.getParam(sqlEntry.pk));
                int count = this.operationEntry.ffzlModify.update(values.toArray());
                if (count > 0) {
                    return Long.parseLong(pw.getParam(sqlEntry.pk));
                }
                return 0l;
            } else {
                throw new RuntimeException("业务主键已存在");
            }
        });
    }

    @Override
    public boolean needInsert(ParamWrap pw) {
        return ObjectUtils.isEmpty(pw.getParam(sqlEntry.pk));
    }
}
