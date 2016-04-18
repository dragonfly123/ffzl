package org.dragonfei;

/**
 * Created by longfei on 16-4-16.
 */
public class HelloTest implements IHelloTest {
    @Override
    public void bb() {
        ((HelloAop)(Object)this).sayHello();
        System.out.println("testbb");
    }
}
