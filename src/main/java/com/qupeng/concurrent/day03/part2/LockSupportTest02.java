package com.qupeng.concurrent.day03.part2;

import java.util.concurrent.locks.LockSupport;

/**
 *LockSupport的等待和唤醒 
 *先唤醒，再等待的情况
 *相当于对唤醒进行了一个抵消作用，
 *本人称之为：0  -1  0作用
 * @author qupeng
 */
public class LockSupportTest02 {
	

	public static void main(String[] args) {
		
		Thread t= new Thread("子线程"){
			@Override
			public synchronized void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"：开始执行……");
				System.out.println(threadName+"：唤醒前---------");
//				this.notify();
				LockSupport.unpark(this);
				System.out.println(threadName+"：唤醒后---------");
				
				System.out.println(threadName+"：等待前---------");
//				try {this.wait();} catch (InterruptedException e) {}
				LockSupport.park();
				System.out.println(threadName+"：等待后---------");
				
				System.out.println(threadName+"：执行结束！");
			}
			
		};
		t.start();
		
		System.out.println("main：执行结束……");
		
	}

}
