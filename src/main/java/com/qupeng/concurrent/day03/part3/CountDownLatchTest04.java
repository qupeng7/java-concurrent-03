package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  向下减的门闩
 *  API的使用讲解
 *  对中断的处理
 * @author qupeng
 */
public class CountDownLatchTest04 {
	
	public static void main(String[] args) {
		
		final Thread mainThread=Thread.currentThread();
		
		final CountDownLatch cdl=new CountDownLatch(1);
		
		new Thread("子线程："){
			
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"开始造原子弹……");
				//做造原子弹需要做20秒
				try {
					Thread.sleep(3_000);
					System.out.println("3秒后中断正在await的主线程，告诉他不要等了");
					mainThread.interrupt();
					Thread.sleep(17_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName+"原子弹制造完毕！");
			}
			
		}.start();
		
		//主线程去等待子线程做完某事在往下执行
		System.out.println("主线程：开始等待子线程……");
		try {
			cdl.await();
		} catch (InterruptedException e) {
			System.out.println("主线程：被通知到不要等了，原子弹不是3秒钟就能造出来的！");
		}
		System.out.println("主线程：执行完毕！");
	}

}
