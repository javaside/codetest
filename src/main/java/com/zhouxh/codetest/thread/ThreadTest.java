package com.zhouxh.codetest.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadTest {
    public static void main(String[] args) {

//        Executor

        BlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(10);

        Thread thread = new Thread(()->{

            while(true){
                Runnable take = null;
                try {
                    take = tasks.take();
                    take.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        thread.start();

        System.out.println(tasks.size());
        try {
            tasks.put(()->{
                    System.out.println("start 1....");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.interrupted();
                    }finally {
                        System.out.println("start 1 end.");
                    }
                }
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tasks.size());

        try {
            tasks.put(()->{
                        System.out.println("start 2....");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.interrupted();
                        }finally {
                            System.out.println("start 2 end.");
                        }
                    }
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tasks.size());
        try {
            tasks.put(()->{
                        System.out.println("start 3....");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.interrupted();
                        }finally {
                            System.out.println("start 3 end.");
                        }
                    }
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(tasks.size());
    }
}

