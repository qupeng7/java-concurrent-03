package com.qupeng.concurrent.day03.part2;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport的应用
 * 用LockSupport实现非重入锁
 * @author Peter
 */
public class LockSupportTest05 {
	
	private static MyLock lock=new MyLock();
	
	public static void main(String[] args) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"：进入同步区域……");
		testSync_1();
		System.out.println(threadName+"：离开同步区域……");
	}
	
	private static /*synchronized*/ void testSync_1(){
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"：开始执行testSync_1方法……");
		lock.lock();
		System.out.println(threadName+"：准备进入testSync_2方法……");
		testSync_2();
		System.out.println(threadName+"：执行了testSync_2中的逻辑！！！");
		lock.unlock();
		System.out.println(threadName+"：执行testSync_1方法结束！！！");
	}
	
	private static /*synchronized*/ void testSync_2(){
		String threadName = Thread.currentThread().getName();
		lock.lock();
		System.out.println(threadName+"：执行了testSync_2中的逻辑！！！");
		lock.unlock();
	}
	
	
	
	static class MyLock{
		
	    private boolean isLocked = false;
	    
	    public synchronized void lock(){
	        while(isLocked){
	           LockSupport.park();
	        }
	        isLocked = true;
	    }
	    
	    public synchronized void unlock(){
	        isLocked = false;
	        LockSupport.unpark(Thread.currentThread());
	    }
	}

}



