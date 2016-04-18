package org.dragonfei;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by longfei on 16-4-17.
 */
public class AspectTest {
    public  void before(JoinPoint jp){
        System.out.println("before");
    }
}
