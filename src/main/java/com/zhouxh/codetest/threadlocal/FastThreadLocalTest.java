package com.zhouxh.codetest.threadlocal;

import com.zhouxh.codetest.comm.ScreenUtils;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.io.IOException;

public class FastThreadLocalTest {

    //保证每个线程都持有一个RedisClient实例。
    private static FastThreadLocal<RedisClient> REDIS_CLIENT_HOLDER =  new FastThreadLocal<RedisClient>(){
        /**
         * 当前线程没有RedisClient实例，会调用此方法初始化RedisClient实例。
         * @return
         */
        @Override
        protected RedisClient initialValue() {
            return new RedisClient();
        }
    };

    //保证每个线程都持有一个MySQLClient实例。
    private static FastThreadLocal<MySQLClient> MYSQL_CLIENT_HOLDER =  new FastThreadLocal<MySQLClient>(){
        @Override
        protected MySQLClient initialValue() {
            return new MySQLClient();
        }
    };



    public static void main(String[] args) throws IOException {


        for(int i=0; i<1; i++){
            Thread thread = new FastThreadLocalThread(()->{

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
        ScreenUtils.pause();
    }
}
