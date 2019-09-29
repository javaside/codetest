package com.zhouxh.codetest.lock;

import com.zhouxh.codetest.comm.ScreenUtils;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            //同步代码块
        }finally {
            lock.unlock();
        }


        new Thread(()->{
            lock.lock();

            try {
                ScreenUtils.pause();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }).start();



        ScreenUtils.pause();
    }
}
