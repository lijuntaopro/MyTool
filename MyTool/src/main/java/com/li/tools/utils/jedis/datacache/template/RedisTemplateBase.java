package com.li.tools.utils.jedis.datacache.template;

import com.li.tools.utils.jedis.RedisPoolUtil;

import redis.clients.jedis.JedisPool;


/**
 * @author lijuntao
 * @date 2016-9-20
 * 任意模板都要继承它，现在没什么用
 * 这一层的作用在于使用jedis的连接池，来获取对象
 */

public class RedisTemplateBase {
    protected JedisPool pool = RedisPoolUtil.getInstance();
}
