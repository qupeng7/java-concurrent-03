package com.qupeng.concurrent.day03.part2;

import java.util.concurrent.locks.LockSupport;

/**
 *LockSupport的等待和唤醒 
 * @author qupeng
 */
public class LockSupportTest01 {
	

	public static void main(String[] args) {
		
		Thread t= new Thread("子线程"){
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"：开始执行……");
				System.out.println(threadName+"：睡眠中……");
				LockSupport.park();
				System.out.println(threadName+"：执行结束！");
			}
			
		};
		t.start();
		try{Thread.sleep(1000);}catch(Exception e){}
		System.out.println("main：已唤醒子线程1次");
		LockSupport.unpark(t);
		System.out.println("main：执行结束……");
		
	}

}
