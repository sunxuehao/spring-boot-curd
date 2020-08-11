package org.mall.java;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface testI {
    void hello();
}

class Test implements testI {
    @Override
    public void hello() {
        System.out.println("hello world~");
    }
}

public class Run {


    public static void main(String[] args) {
        Test test = new Test();

        testI testP = (testI) Proxy.newProxyInstance(test.getClass().getClassLoader(),
                test.getClass().getInterfaces(), (proxy, method, arg) -> {

                    if (method.getName() == "hello") {
                        System.out.println("我是代理类");
                        method.invoke(test, arg);
                    } else {
                        method.invoke(test, arg);
                    }
                    return null;
                });
        testP.hello();
    }


}
