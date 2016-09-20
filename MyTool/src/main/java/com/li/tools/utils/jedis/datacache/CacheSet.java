package com.li.tools.utils.jedis.datacache;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijuntao
 * @date 2016-9-18
 */
public class CacheSet<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private String prefix;
    private String suffix;
    private String key;
    private Set<T> entitySet;
    
    public CacheSet() {
	super();
    }
    public CacheSet(String prefix, String suffix, String key,
	    Set<T> entitySet) {
	super();
	this.prefix = prefix;
	this.suffix = suffix;
	this.key = key;
	this.entitySet = entitySet;
    }
    public String getPrefix() {
        return prefix==null?"":prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getSuffix() {
        return suffix==null?"":suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getKey() {
        return key==null?"":key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Set<T> getEntitySet() {
	if(entitySet==null)
	    entitySet = new HashSet<T>();
        return entitySet;
    }
    public void setEntitySet(Set<T> entitySet) {
        this.entitySet = entitySet;
    }
    @Override
    public String toString() {
	return "CacheSet [prefix=" + prefix + ", suffix=" + suffix + ", key="
		+ key + ", entitySet=" + entitySet + "]";
    }
}
