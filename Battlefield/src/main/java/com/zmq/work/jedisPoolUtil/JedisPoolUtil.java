package com.zmq.work.jedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool=null;
    private JedisPoolUtil(){}
    public static void release(JedisPool jedisPool, Jedis jedis){
        if (null!=jedis){
            jedisPool.returnResourceObject(jedis);
        }
    }
    public static JedisPool getJedisPoolInstance(){
        if (null==jedisPool){
            synchronized (JedisPoolUtil.class){
                if (null==jedisPool){
                    JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
                    jedisPoolConfig.setMaxIdle(32);
                    jedisPoolConfig.setMaxWaitMillis(100*100);
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool=new JedisPool(jedisPoolConfig,"localhost",6379);
                }
            }
        }
        return jedisPool;
    }

}
