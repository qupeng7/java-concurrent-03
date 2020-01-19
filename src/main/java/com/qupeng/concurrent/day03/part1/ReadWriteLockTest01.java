package com.qupeng.concurrent.day03.part1;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读写锁的用法
 * 将锁的类别更加细化的一种解决问题的方式，
 * 能更好的提高效率
 * @author qupeng
 */
public class ReadWriteLockTest01 {
	
	public static void main(String[] args) {
		final BigHouse bigHouse = new BigHouse();
		//创建3个人去看黄金
		for(int i=0;i<3;i++){
			new Thread("看的人"+(i+1)){
				public void run(){
					while(true){
						bigHouse.see();
					}
				}
			}.start();
		}
		//创建2个人去拿黄金
		for (int i = 0; i < 2; i++) {
			new Thread("拿的人"+(i+1)){
				public void run(){
					while(true){
						bigHouse.change(1);
					}
				}			
				
			}.start();
		}

	}

}


class  BigHouse{
	
	/*
	 * 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	 */
	private int count =10;
	
	public String name="黄金";
	
	/**
	 * 这个房子上有一个读写锁
	 */
	private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	
	public   void  see() {
//		readWriteLock.readLock().lock();
		try {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName+ "：正走进一点准备看这个"+name);
			//走到能看清黄金的地方用了2秒
			Thread.sleep(2_000);
			System.out.println(threadName + "：已经看清了是：" +name+"有"+ count+"块");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	finally{
//			readWriteLock.readLock().unlock();
		}
	}
	
	public /*synchronized*/ void change(int num){
//		readWriteLock.writeLock().lock();
		try {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName+ "：正走进一点准备拿走这个"+name);		
			//走到能拿走这个黄金的地方用了3秒
			Thread.sleep(1_000);
			this.count=count-num;		
			System.out.println(threadName + "：拿走1块黄金后还有：" + count+"块");
		} catch (InterruptedException e) {
			
		}finally{
//			readWriteLock.writeLock().unlock();
		}
	}
	
	
}
