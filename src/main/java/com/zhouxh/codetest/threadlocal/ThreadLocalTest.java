package com.zhouxh.codetest.threadlocal;

import java.io.IOException;

public class ThreadLocalTest {

    //保证每个线程都持有一个RedisClient实例。
    private static ThreadLocal<RedisClient> REDIS_CLIENT_HOLDER =  new ThreadLocal<RedisClient>(){
        /**
         * 当前线程没有RedisClient实例，会调用此方法初始化RedisClient实例。
         * @return
         */
        @Override
        protected RedisClient initialValue() {
            return new RedisClient();
        }
    };

    //保证每个线程都持有一个RedisClient实例。
    private static ThreadLocal<MySQLClient> MYSQL_CLIENT_HOLDER =  new ThreadLocal<MySQLClient>(){
        /**
         * 当前线程没有MySQLClient实例，会调用此方法初始化MySQLClient实例。
         * @return
         */
        @Override
        protected MySQLClient initialValue() {
            return new MySQLClient();
        }
    };



    public static void main(String[] args) throws IOException {


        for(int i=0; i<1; i++){
            Thread thread = new Thread(()->{

                String threadName = Thread.currentThread().getName();

                RedisClient redisClient0 = REDIS_CLIENT_HOLDER.get();
                RedisClient redisClient1 = REDIS_CLIENT_HOLDER.get();

                System.out.printf("%s, redisClient0 == redisClient1 is %s\n",threadName, redisClient0 == redisClient1);

                MySQLClient mySQLClient0 = MYSQL_CLIENT_HOLDER.get();
                MySQLClient mySQLClient1 = MYSQL_CLIENT_HOLDER.get();

                System.out.printf("%s, mySQLClient0 == mySQLClient1 is %s\n",threadName, mySQLClient0 == mySQLClient1);

                redisClient1 = REDIS_CLIENT_HOLDER.get();

                System.out.printf("%s, redisClient0 == redisClient1 is %s\n",threadName, redisClient0 == redisClient1);
            });

            thread.start();
        }


//        System.out.print("请输入任何字符按回车键退出程序。");
//        System.in.read();
    }
}
