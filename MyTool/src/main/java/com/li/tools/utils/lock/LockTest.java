package com.li.tools.utils.lock;

import java.lang.reflect.Field;

import org.junit.Test;

import sun.misc.Unsafe;

public class LockTest {
	
    private static final Unsafe unsafe;
    
    static{
    	Field f;
		try {
			f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
		} catch (Exception e) {
			throw new Error(e);
		}
    }
    
//    @Test
    public void test(){
    	String b = "11123";
    	String a = new String(b);
    	boolean c = unsafe.compareAndSwapObject(this, 1, null, a);
    	System.out.println(c);
    	c = unsafe.compareAndSwapObject(this, 1, a, a);
    	System.out.println(c);
    	c = unsafe.compareAndSwapObject(this, 1, b, null);
    	Object object = unsafe.getAndSetObject(this, 1, null);
    	System.out.println(object);
    	c = unsafe.compareAndSwapObject(this, 1, null, b);
    	System.out.println(c);
    }
}
