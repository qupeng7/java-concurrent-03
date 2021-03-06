package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏：本人比喻为：检测站
 * 使用reset方法
 * @author qupeng
 */
public class CyclicBarrierTest03 {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		
		//公司要在北京开董事会，公司总共3个董事
		final CyclicBarrier barrier=new CyclicBarrier(3);
		
		Thread t1 = new Thread("董事1："){
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				try {
					System.out.println(threadName+"出发前往北京……");
					//董事1到达北京需要1秒
					Thread.sleep(1000);
					System.out.println(threadName+"已到达北京！");
					//getNumberWaiting方法，当调用await之后这个数字就会减1
					int waittingNum =barrier.getNumberWaiting();
					barrier.await();
					if(waittingNum==0){
						System.out.println(threadName+"所有人已经到达北京，召开董事会！");
					}
				} catch (InterruptedException e) {
				} catch (BrokenBarrierException e) {
				}
			}
		};
		
		Thread t2 = new Thread("董事2："){
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				try {
					System.out.println(threadName+"出发前往北京……");
					//董事2到达北京需要2秒
					Thread.sleep(2000);
					System.out.println(threadName+"已到达北京！");
					//getNumberWaiting方法，当调用await之后这个数字就会减1
					int waittingNum =barrier.getNumberWaiting();
					barrier.await();
					if(waittingNum==0){
						System.out.println(threadName+"所有人已经到达北京，召开董事会！");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
				}
			}
		};
		
		Thread t3 = new Thread("董事3："){
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				try {
					System.out.println(threadName+"出发前往北京……");
					//董事3到达北京需要4秒
					Thread.sleep(4000);
					System.out.println(threadName+"已到达北京！");
					//getNumberWaiting方法，当调用await之后这个数字就会减1
					int waittingNum =barrier.getNumberWaiting();
					barrier.await();
					if(waittingNum==0){
						System.out.println(threadName+"所有人已经到达北京，召开董事会！");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
				}
			}
		};
		t1.start();
		t2.start();
		t3.start();
		//主线程在1.5秒后调用reset方法，表示自己也要参加董事会
		Thread.sleep(1500);
		System.out.println("主线程：已经有"+barrier.getNumberWaiting()+"个董事到达北京！");
		barrier.reset();//将倒计时重置
		//主线程花了2秒到达北京
		Thread.sleep(2000);
		System.out.println("主线程：已经到达北京！");
		barrier.await();
		System.out.println("主线程执行完毕！");
	}

}
