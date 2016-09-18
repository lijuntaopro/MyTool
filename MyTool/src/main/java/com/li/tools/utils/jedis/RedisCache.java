package com.li.tools.utils.jedis;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisCache{
	private static Log log = LogFactory.getLog(RedisCache.class);  
    /** The ReadWriteLock. */  
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
      
    private String id;  
    public RedisCache(final String id) {  
            if (id == null) {  
                    throw new IllegalArgumentException("必须传入ID");  
            }  
            log.debug("MybatisRedisCache:id=" + id);  
            this.id=id;  
    }  
      
    public String getId() {  
            return this.id;  
    }  

    public int getSize() {  
            Jedis jedis = null;  
            JedisPool jedisPool = null;  
            int result = 0;  
            boolean borrowOrOprSuccess = true;  
            try {  
                    jedis   = CachePool.getInstance().getJedis();  
                    jedisPool = CachePool.getInstance().getJedisPool();  
                    result = Integer.valueOf(jedis.dbSize().toString());  
            } catch (JedisConnectionException e) {  
                    borrowOrOprSuccess = false;  
                    if (jedis != null)  
                            jedisPool.returnBrokenResource(jedis);  
            } finally {  
                    if (borrowOrOprSuccess)  
                            jedisPool.returnResource(jedis);  
            }  
            return result;  
               
    }  

    public void putObject(Object key, Object value) { 
    	    
    	    System.out.println("put:key:"+key.hashCode());
    	    System.out.println("put:value:"+value);
            if(log.isDebugEnabled())  
            log.debug("putObject:" + key.hashCode() + "=" + value);  
            if(log.isInfoEnabled())  
            log.info("put to redis sql :" +key.toString());  
            Jedis jedis = null;  
            JedisPool jedisPool = null;  
            boolean borrowOrOprSuccess = true;  
            try {  
                    jedis   = CachePool.getInstance().getJedis();  
                    jedisPool = CachePool.getInstance().getJedisPool();  
                    jedis.set(SerializeUtil.serialize(key.hashCode()), SerializeUtil.serialize(value));
                    long i = System.currentTimeMillis();
                    log.info(i);
                    jedis.set(""+i, ""+i);
            } catch (JedisConnectionException e) {  
                    borrowOrOprSuccess = false;  
                    if (jedis != null)  
                            jedisPool.returnBrokenResource(jedis);  
            } finally {  
                    if (borrowOrOprSuccess)  
                            jedisPool.returnResource(jedis);  
            }  
              
    }  

    public Object getObject(Object key) {  
    	    System.out.println("get:"+Arrays.toString(SerializeUtil.serialize(key.hashCode())));
            Jedis jedis = null;  
            JedisPool jedisPool = null;  
            Object value = null;  
            boolean borrowOrOprSuccess = true;  
            try {  
                    jedis   = CachePool.getInstance().getJedis();  
                    jedisPool = CachePool.getInstance().getJedisPool();  
                    value  = SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key.hashCode())));  
            } catch (JedisConnectionException e) {  
                    borrowOrOprSuccess = false;  
                    if (jedis != null)  
                            jedisPool.returnBrokenResource(jedis);  
            } finally {  
                    if (borrowOrOprSuccess)  
                            jedisPool.returnResource(jedis);  
            }  
            if(log.isDebugEnabled());  
            log.info("getObject:" + key.hashCode() + "=" + value);  
            return value;  
    }  

    public Object removeObject(Object key) {  
            Jedis jedis = null;  
            JedisPool jedisPool = null;  
            Object value = null;  
            boolean borrowOrOprSuccess = true;  
            try {  
                    jedis   = CachePool.getInstance().getJedis();  
                    jedisPool = CachePool.getInstance().getJedisPool();  
                    value  = jedis.expire(SerializeUtil.serialize(key.hashCode()), 0);  
            } catch (JedisConnectionException e) {  
                    borrowOrOprSuccess = false;  
                    if (jedis != null)  
                            jedisPool.returnBrokenResource(jedis);  
            } finally {  
                    if (borrowOrOprSuccess)  
                            jedisPool.returnResource(jedis);  
            }  
            if(log.isDebugEnabled())  
            log.debug("getObject:" + key.hashCode() + "=" + value);  
            return value;  
    }  

    public void clear() {  
            Jedis jedis = null;  
            JedisPool jedisPool = null;  
            boolean borrowOrOprSuccess = true;  
            try {  
                    jedis   = CachePool.getInstance().getJedis();  
                    jedisPool = CachePool.getInstance().getJedisPool();  
                    jedis.flushDB();  
                    jedis.flushAll();  
            } catch (JedisConnectionException e) {  
                    borrowOrOprSuccess = false;  
                    if (jedis != null)  
                            jedisPool.returnBrokenResource(jedis);  
            } finally {  
                    if (borrowOrOprSuccess)  
                            jedisPool.returnResource(jedis);  
            }  
    }  

    public ReadWriteLock getReadWriteLock() {  
            return readWriteLock;  
    }  
   /** 
    *  
   * @ClassName: CachePool 
   * @Description: TODO(单例Cache池) 
   * @author LiuYi 
   * @date 2014年6月17日 上午10:50:52 
   * 
    */  
    public static class CachePool {  
            JedisPool pool;  
            private static final CachePool cachePool = new CachePool();  
              
            public static CachePool getInstance(){  
                    return cachePool;  
            }  
            private CachePool() {  
                    JedisPoolConfig config = new JedisPoolConfig();  
                    config.setMaxIdle(100);  
                    config.setMaxWait(1000l);
                    InputStream is = 
//                    		getClass().getClassLoader().getResourceAsStream("/redis.properties");
//            		getClass().getResourceAsStream("classpath:redis.properties");
                    		
//                  Thread.currentThread().getClass().getClassLoader().getResourceAsStream("/redis.properties");
//            		Thread.currentThread().getClass().getResourceAsStream("/redis.properties");
//                    		可以的
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties");
                    
                    Properties pl =  new Properties();
                    try {
						pl.load(is);
					} catch (IOException e) {
						log.error("加载redis.properties文件失败");
					}
//                    Properties pl = new ResourceUtil().getResource();
                    pool = new JedisPool(config,"192.168.7.97",6379);
            }  
            public  Jedis getJedis(){  
                    Jedis jedis = null;  
                    boolean borrowOrOprSuccess = true;  
                    try {  
                            jedis = pool.getResource();  
                    } catch (JedisConnectionException e) {  
                            borrowOrOprSuccess = false;  
                            if (jedis != null)  
                                    pool.returnBrokenResource(jedis);  
                    } finally {  
                            if (borrowOrOprSuccess)  
                                    pool.returnResource(jedis);  
                    }  
                    jedis = pool.getResource();  
                    return jedis;  
            }  
              
            public JedisPool getJedisPool(){  
                    return this.pool;  
            }  
              
    }  
      
      
    public static class SerializeUtil {  
            public static byte[] serialize(Object object) {  
                    ObjectOutputStream oos = null;  
                    ByteArrayOutputStream baos = null;  
                    try {  
                            // 序列化  
                            baos = new ByteArrayOutputStream();  
                            oos = new ObjectOutputStream(baos);  
                            oos.writeObject(object);  
                            byte[] bytes = baos.toByteArray();  
                            return bytes;  
                    } catch (Exception e) {  
                            e.printStackTrace();  
                    }  
                    return null;  
            }  

            public static Object unserialize(byte[] bytes) {  
                    if(bytes == null)return null;  
                    ByteArrayInputStream bais = null;  
                    try {  
                            // 反序列化  
                            bais = new ByteArrayInputStream(bytes);  
                            ObjectInputStream ois = new ObjectInputStream(bais);  
                            return ois.readObject();  
                    } catch (Exception e) {  
                            e.printStackTrace();  
                    }  
                    return null;  
            }  
    }  
}
