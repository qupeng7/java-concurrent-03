package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏：本人比喻为：检测站
 * 对中断的处理
 * @author qupeng
 */
public class CyclicBarrierTest02 {

	public static void main(String[] args) throws InterruptedException {
		
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
					System.out.println(threadName+"我被杀掉了！");
				} catch (BrokenBarrierException e) {
					System.out.println(threadName+"有董事被杀掉了！");
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
					System.out.println(threadName+"此时isBroken的状态为："+barrier.isBroken());
					barrier.await();
					if(waittingNum==0){
						System.out.println(threadName+"所有人已经到达北京，召开董事会！");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					System.out.println(threadName+"有董事被杀掉了！");
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
					System.out.println(threadName+"此时isBroken的状态为："+barrier.isBroken());
					barrier.await();
					if(waittingNum==0){
						System.out.println(threadName+"所有人已经到达北京，召开董事会！");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					System.out.println(threadName+"有董事被杀掉了！");
				}
			}
		};
		t1.start();
		t2.start();
		t3.start();
		//主线程在2.5秒后中断董事1
		Thread.sleep(2500);
		t1.interrupt();
		System.out.println("主线程执行完毕！");
	}

}
