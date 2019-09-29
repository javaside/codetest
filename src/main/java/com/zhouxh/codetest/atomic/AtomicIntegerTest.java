package com.zhouxh.codetest.atomic;

import com.zhouxh.codetest.comm.ScreenUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {


   public static AtomicInteger integer = new AtomicInteger();

    /**
     * 多线程调用
     */
   public void test(){
       integer.incrementAndGet();
       // some code....
   }

    public static void main(String[] args) {
        AtomicInteger atomicInt0 = new AtomicInteger();
        AtomicInteger atomicInt1 = new AtomicInteger();

        atomicInt0.incrementAndGet();
        atomicInt1.incrementAndGet();

        ScreenUtils.pause();
    }
}
