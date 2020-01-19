package com.qupeng.concurrent.day03.lookback;


/**
 * 观察继承Thread类的时候，
 * 对run方法加上synchronized是否能达到同步效果
 * 
 * 使用继承Thread类的方式在run方法上加synchronized
 * 达不到同步的效果，因为每个线程锁的都是自己，而这
 * 每个线程又是不同的对象，所以达不到同步效果。
 * @author Peter
 */
public class WatchThreadTest{

	public static void main(String[] args) {
		
		for (int i = 1; i <= 3; i++) {
			MyThread mt = new MyThread("线程"+i);
			//设置线程的名称，方便查看在执行哪个对象的run()
			mt.start();
		}
	}
	
	
	
	
	static class MyThread extends Thread{
		
		public MyThread(String name) {
			super(name);
		}
		
		@Override
		public synchronized void run() {
			String threadName = Thread.currentThread().getName();
//			synchronized (WatchThreadTest.class) {
				//循环10次，每1秒钟循环一次
				for (int i = 1; i <= 10; i++) {
					System.out.println(threadName+"：执行第"+i+"次循环……");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//synchronized结束
//			}
			
		}
	}
}