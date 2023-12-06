package com.example.springaaaaaaaa;

import org.junit.jupiter.api.Test;

/**
 * ClassName: ThreadLocalTest
 * Package: com.example.springaaaaaaaa
 */
public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        ThreadLocal tl=new ThreadLocal();
        new Thread(()->{
            tl.set("消炎");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"蓝色").start();
        new Thread(()->{
            tl.set("药尘");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"绿色").start();
    }
}
