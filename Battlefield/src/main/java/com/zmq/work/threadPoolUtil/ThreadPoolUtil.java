package com.zmq.work.threadPoolUtil;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    private static volatile ThreadPoolExecutor executor=null;
    public ThreadPoolUtil(){}
    public static ThreadPoolExecutor getExecutor(){
        if (null==executor){
            synchronized (ThreadPoolUtil.class){
                if (null ==executor){
                    executor=new ThreadPoolExecutor(8,10,60, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(5));
                }
            }
        }
        return executor;
    }
    public static void delExecutor(ThreadPoolExecutor executor){
        executor.shutdown();
    }


}
