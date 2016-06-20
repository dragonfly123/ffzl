package org.dragonfei.ffzl.params.transaction;

import org.dragonfei.ffzl.utils.spring.SpringContextUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by longfei on 16/6/20.
 */
public class TransactionFactory {
    private static TransactionFactory transationFactory;
    private TransactionFactory(){}
    public static TransactionFactory getInstance(){
        if(transationFactory == null){
            synchronized (TransactionFactory.class){
                transationFactory = new TransactionFactory();
            }
        }
        return transationFactory;
    }

    private PlatformTransactionManager transactionManager = SpringContextUtils.getBean("transactionManager",PlatformTransactionManager.class);
    private TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    public <T>T execute(TransactionCallback<T> action){
        return transactionTemplate.execute(action);
    }
}
