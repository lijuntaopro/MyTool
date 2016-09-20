package com.li.tools.utils.jedis.datacache;
/**
 * @author lijuntao
 * @date 2016-9-18
 */
public class ChannelCacheEntity extends CacheEntity<String>{
    private static final long serialVersionUID = 1L;
    private String name;
    private String detail;
    
    public ChannelCacheEntity(String id,String name, String detail) {
	super(id);
	this.name = name;
	this.detail = detail;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    @Override
    public String toString() {
	return "ChannelCacheEntity [name=" + name + ", detail=" + detail
		+ ", id=" + id + "]";
    }
}
