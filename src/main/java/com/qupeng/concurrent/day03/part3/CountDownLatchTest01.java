package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.CountDownLatch;

/**
 *  向下减的门闩
 *  实现并行处理最后汇总结果的功能
 *  
 * 以 汽车生产为例
 * @author Peter
 */
public class CountDownLatchTest01 {
	

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch cdl=new CountDownLatch(3);
		
		String threadName = Thread.currentThread().getName()+"：";
		//创建3条线程去做这三件事
		new Thread("线程1："){
			
			@Override
			public void run() {
				try {
					productMotor();
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		
		new Thread("线程2："){
			
			@Override
			public void run() {
				try {
					productShell();
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		
		new Thread("线程3："){
			
			@Override
			public void run() {
				try {
					productTyre();
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		
		//线程4为4S店派到生产厂家的监视者
		/*new Thread("线程4："){
			
			@Override
			public void run() {
				try {
					//可以有多个线程对其进行await
					cdl.await();
					System.out.println(Thread.currentThread().getName()+"已经生产好了一辆汽车的所有部件了！");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();*/
		
		//等待汽车各个部分生产完毕
		cdl.await();
		System.out.println(threadName+"开始组装汽车……");
		//模拟组装生产汽车需要1秒
		Thread.sleep(1000);
		System.out.println(threadName+"汽车生产完毕，运到销售中心销售。");
	}
	
	private static  void productMotor() throws InterruptedException{
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"开始生产发动机……");
		//生产发动机需要5秒
		Thread.sleep(5_000);
		System.out.println(threadName+"发动机已生产完毕！");
	}
	
	private static void productShell() throws InterruptedException{
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"开始生产汽车外壳……");
		//生产汽车外壳需要3秒
		Thread.sleep(3_000);
		System.out.println(threadName+"汽车外壳已生产完毕！");
	}
	
	private static  void productTyre() throws InterruptedException{
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"开始生产轮胎……");
		//生产汽车轮胎需要1秒
		Thread.sleep(1_000);
		System.out.println(threadName+"轮胎已生产完毕！");
	}

}
