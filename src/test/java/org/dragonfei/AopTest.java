package org.dragonfei;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by longfei on 16-4-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-*.xml")
public class AopTest {


    @Before
    public void setup(){
    }
    @Test
    public void testAop(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext("test-aop.xml");
        HelloAop helloAop =  ac.getBean("helloAop",HelloAop.class);
        helloAop.sayHello();
        System.out.println("================================");
        HelloAop helloAop1 = ac.getBean("proxyhelloAop",HelloAop.class);
        helloAop1.sayHello();
    }
}
