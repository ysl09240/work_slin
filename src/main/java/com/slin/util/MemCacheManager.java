package com.slin.util;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;

public class MemCacheManager {
private static MemCacheManager instance = new MemCacheManager();
    
    /** XMemCache允许开发者通过设置节点权重来调节MemCache的负载，设置的权重越高，该MemCache节点存储的数据越多，负载越大 */
    //"127.0.0.1:11211 127.0.0.2:11211 127.0.0.3:11211"), new int[]{1, 3, 5}
    private static MemcachedClientBuilder mcb = 
            new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11211"), new int[]{1});
    private static MemcachedClient mc = null;
    
    /** 初始化加载客户端MemCache信息 */
    static
    {
        mcb.setCommandFactory(new BinaryCommandFactory()); // 使用二进制文件
        mcb.setConnectionPoolSize(10); // 连接池个数，即客户端个数
        mcb.getConfiguration().setStatisticsServer(false);
        try
        {
            mc = mcb.build();
            mc.setEnableHeartBeat(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    private MemCacheManager()
    {
        
    }
    
    public static MemCacheManager getInstance()
    {
        return instance;
    }
    
    /** 向MemCache服务器设置数据
     * key 
     * expiry 秒
     */
    public void set(String key, int expiry, Object obj) throws Exception
    {
        mc.set(key, expiry, obj);
    }
    
    /** 从MemCache服务器获取数据 */
    public <Object>Object get(String key) throws Exception
    {
    	Object bb =  mc.get(key);
        return bb;
    }
    
    public boolean delete(String key) throws Exception
    {
    	boolean dd = mc.delete(key);
    	return dd;
    }
    
    /**
     * MemCache通过compare and set即cas协议实现原子更新，类似乐观锁，每次请求存储某个数据都要附带一个cas值，MemCache
     * 比对这个cas值与当前存储数据的cas值是否相等，如果相等就覆盖老数据，如果不相等就认为更新失败，这在并发环境下特别有用
     */
    public boolean update(String key, Integer i) throws Exception
    {
        GetsResponse<Integer> result = mc.gets(key);
        long cas = result.getCas();
        // 尝试更新key对应的value
        if (!mc.cas(key, 0, i, cas))
        {
            return false;
        }
        return true;
    }
    
    
    public static void main(String args[]){
    	MemCacheManager mc = MemCacheManager.getInstance();
    	try {
    		mc.set("dd", 30, "bbb");
			String keyValue = mc.get("dd");
			System.out.println(keyValue);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
            if (mc != null) {  
                try {  
                   // memcachedClient.;  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        } 
    }
}
