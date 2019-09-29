package com.zhouxh.codetest.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionTest {

    public static void main(String[] args) {
        final AvailResource resource = new AvailResource();

        //创建两个生产线程
        new Thread(()->{while (true)resource.producer();}).start();
        new Thread(()->{while (true)resource.producer();}).start();

        //创建两个消费线程
        new Thread(()->{while (true)resource.comsumer();}).start();
        new Thread(()->{while (true)resource.comsumer();}).start();
    }

    static class AvailResource{
        private int available = 8; //定义可用资源个数，8：表示当前有8个可用资源。
        private int maxAvailable = 8;   //最大可用资源

        private ReentrantLock lock = new ReentrantLock();
        private Condition producerCon = lock.newCondition(); //生产者 条件等待队列
        private Condition comsumerCon = lock.newCondition(); //消费者 条件等待队列

        /**
         * 每次减少一个可用额度
         */
        public void comsumer(){
            lock.lock();
            try{
                while(available < 1){
                    //减少一个可用度小于0 表明当前 没有可用资源，需要进入条件等待队列
                    comsumerCon.await();
                }
                available --;

                this.print();//打印当前资源数
                //唤醒生产条件等待的线程
                producerCon.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 每次增加一个可用额度
         */
        public void producer(){
            lock.lock();
            try{
                while (available == maxAvailable){
                    //可用资源达到最大可用资源数，需要进入条件等待队列
                    producerCon.await();
                }

                available++;
                this.print();//打印当前资源数
                comsumerCon.signal();//唤醒条件等待的消费者线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void print(){
            System.out.println(toString());
        }
        @Override
        public String toString() {

            return Thread.currentThread().getName() + ",AvailResource{" +
                    "available=" + available +
                    ", maxAvailable=" + maxAvailable +
                    '}';
        }
    }
}
