package com.li.tools.utils.lock;

import org.junit.Test;

public class LockUtils {
	
	/**
	 * 
	 * @author lijuntao
	 * @date 2019年3月26日
	 */
	@Test
	public void test(){
		final StringLock lock = new StringLock();
		final Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				while(i-- > 0){
					run1(lock);
				}
			}
		});
		final Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				while(i-- > 0){
					run2(lock);
				}
			}
		});
		Thread t3 = new Thread(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				while(i-- > 0){
					run3(lock);
				}
			}
		});
		final Thread t4 = new Thread(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				while(i-- > 0){
					run4(lock);
				}
			}
		});
		final Thread t5 = new Thread(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				while(i-- > 0){
					run4(lock);
				}
			}
		});
		final Thread t6 = new Thread(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				while(i-- > 0){
					run3(lock);
				}
				t5.interrupt();
				t.interrupt();
			}
		});
		t.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		try {
			t.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕");
	}
	
	public void run1(StringLock lock){
		String name = Thread.currentThread().getName();
		System.out.println(name + ":开始运行");
		String str = "112";
		System.out.println(name + ":准备获取锁");
		lock.lock(str);
		System.out.println(name + ":获取锁成功++++++++++++++++");
		try{
			System.out.println(name + ":开始睡眠1s");
			Thread.sleep(1000);
			System.out.println(name + ":结束睡眠");
		}catch(Exception e){
			
		}finally{
			System.out.println(name + ":准备释放锁");
			lock.unlock(str);
			System.out.println(name + ":释放锁成功-------------");
		}
		System.out.println(name + ":再次睡眠2s");
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(name + ":结束再次睡眠");
	}
	
	public void run2(StringLock lock){
		String name = Thread.currentThread().getName();
		String str = "112";
		System.out.println(name + ":开始运行");
		System.out.println(name + ":开始睡眠1s");
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(name + ":结束睡眠");
		System.out.println(name + ":准备获取锁");
		lock.lock(str);
		System.out.println(name + ":获取锁成功++++++++++++++++");
		try{
			System.out.println(name + ":执行");
		}catch(Exception e){
			
		}finally{
			lock.unlock(str);
		}
		System.out.println(name + ":释放锁成功-----------------");
	}
	
	public void run3(StringLock lock){
		String name = Thread.currentThread().getName();
		String str = "112";
		System.out.println(name + ":准备获取锁");
		lock.lock(str);
		System.out.println(name + ":获取锁成功+++++++++++++++++++");
		try{
			System.out.println(name + ":执行");
		}finally{
			lock.unlock(str);
			System.out.println(name + ":释放锁成功--------------------");
		}
	}
	
	public void run4(StringLock lock){
		String name = Thread.currentThread().getName();
		String str = "112";
		System.out.println(name + ":准备获取锁");
		lock.lock(str);
		System.out.println(name + ":获取锁成功++++++++++++++++++");
		try{
			System.out.println(name + ":执行");
		}finally{
			lock.unlock(str);
			System.out.println(name + ":释放锁成功-------------------");
		}
	}
	
	public void run5(StringLock lock){
		String name = Thread.currentThread().getName();
		String str = "112";
		System.out.println(name + ":准备获取锁");
		lock.lock(str);
		System.out.println(name + ":获取锁成功+++++++++++++++++++");
		try{
			System.out.println(name + ":执行");
		}finally{
			lock.unlock(str);
			System.out.println(name + ":释放锁成功---------------------");
		}
	}
	
	public void run6(StringLock lock){
		String name = Thread.currentThread().getName();
		String str = "112";
		System.out.println(name + ":准备获取锁");
		lock.lock(str);
		System.out.println(name + ":获取锁成功++++++++++++++");
		try{
			System.out.println(name + ":执行");
		}finally{
			lock.unlock(str);
			System.out.println(name + ":释放锁成功-------------------");
		}
	}
	
}
