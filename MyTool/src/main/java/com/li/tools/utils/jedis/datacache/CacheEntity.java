package com.li.tools.utils.jedis.datacache;

import java.io.Serializable;

/**
 * @author lijuntao
 * @date 2016-9-18
 */
public class CacheEntity<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    protected T id;
    public CacheEntity(){}
    public CacheEntity(T id){this.id = id;}
    public T getId() {
        return id;
    }
    public void setId(T id) {
        this.id = id;
    }
    
}
