package org.dragonfei;

import java.io.Serializable;

/**
 * Created by longfei on 16-4-13.
 */
public class HelloAop implements Serializable{
    public void sayHello(){
        System.out.println("hello Aop");
    }

    public void sayHello(String hello){
        System.out.println("hello "+ hello);
    }


    public void sayHello(String hello,String hello2){
        System.out.println("hello "+ hello+"#"+hello2);
    }

    public void sayHello(TestType a){
        System.out.println("test type");
    }
    public void sayHello(TestTypeSub a){
        System.out.println("test subtype");
    }
}
