package org.mall.java;

import jdk.internal.org.objectweb.asm.Handle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Handler;

public class Main {
    public static void main(String[] args) {
        JavaSun javaSun = new JavaSun();

//        参数一:生成代理对象使用哪个类装载器【一般我们使用的是被代理类的装载器】
//        参数二:生成哪个对象的代理对象，通过接口指定【指定要被代理类的接口】
//        参数三:生成的代理对象的方法里干什么事【实现handler接口，我们想怎么实现就怎么实现】

        Programmer programmerWater = (Programmer) Proxy.newProxyInstance(
                javaSun.getClass().getClassLoader(),
                javaSun.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getName().equals("coding")){
                            System.out.println("代理开始");
                            method.invoke(javaSun,args);
                            System.out.println("代理成功");
                        }else{

                        }
                        return null;
                    }
                }
        );
        programmerWater.coding();

    }
}
