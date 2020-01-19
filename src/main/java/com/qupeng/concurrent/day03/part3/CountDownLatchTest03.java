package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  向下减的门闩
 *  API的使用讲解
 *  
 * @author Peter
 */
public class CountDownLatchTest03 {
	
	public static void main(String[] args) throws InterruptedException {
		
		final CountDownLatch cdl=new CountDownLatch(1);//为0时、为-1时
		
		new Thread("子线程："){
			
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"开始造原子弹……");
				//做造原子弹需要做20秒
				try {
					Thread.sleep(20_000);
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName+"原子弹制造完毕！");
			}
			
		}.start();
		
		//主线程去等待子线程做完某事在往下执行
		System.out.println("主线程：开始等待子线程……");
		//如果先countDown再进行await，那么就没有效果
//		cdl.countDown();
		cdl.await();
		//await的效果不能叠加
//		cdl.await();
		
		//设置超时时间，超过这个时间就不再等待了
//		System.out.println("主线程：我只等2秒，2秒后还造不出原子弹，我就不等了！");
//		cdl.await(2, TimeUnit.SECONDS);
//		System.out.println("主线程：我已经等了2秒了，还没造出原子弹，不等了，开始打仗吧！");
		
		System.out.println("主线程：执行完毕！");
	}

}
