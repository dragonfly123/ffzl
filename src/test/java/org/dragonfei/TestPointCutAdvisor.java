package org.dragonfei;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * Created by longfei on 16-4-14.
 */
public class TestPointCutAdvisor extends AbstractGenericPointcutAdvisor {

    private Pointcut pointcut;
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }
}
