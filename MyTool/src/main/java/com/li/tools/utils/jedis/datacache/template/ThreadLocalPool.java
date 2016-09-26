package com.li.tools.utils.jedis.datacache.template;

import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-20
 */
public class ThreadLocalPool {
    public static ThreadLocal<Jedis> thresdJedis = new ThreadLocal<Jedis>();
}
