package com.qupeng.concurrent.day03.part2;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport对interrupt的感知
 * 不需要抛异常直接被中断
 * @author Peter
 */
public class LockSupportTest04 {

	public static void main(String[] args) {
		Thread t= new Thread("子线程"){
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"：开始执行……");
				System.out.println(threadName+"：睡眠中……");
				/*synchronized (this) {
					try {
						//使用wait进行等待
						this.wait();
						//使用sleep进行等待
//						Thread.sleep(Long.MAX_VALUE);
					} catch (InterruptedException e) {
						System.out.println(threadName+"：捕获到中断异常……");
					}
				}*/
				LockSupport.park();
				System.out.println(threadName+"：执行结束！");
			}
			
		};
		t.start();
		System.out.println("main：对子线程进行中断！");
		t.interrupt();
		System.out.println("main：执行结束……");
	}

}
