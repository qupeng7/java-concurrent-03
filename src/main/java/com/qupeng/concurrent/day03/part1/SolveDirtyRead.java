package com.qupeng.concurrent.day03.part1;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 解决脏读问题
 * 这里使用读写锁去实现同步，
 * 就比直接用synchronized高效很多
 * @author Peter
 */
public class SolveDirtyRead {
	
	static class Person{
		
		private String name;
		
		private boolean hitedSuccess=false;
		
		private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
		
		public  void hited(String name,boolean hitedSuccess){
			readWriteLock.writeLock().lock();
			try {
				this.name=name;
				Thread.sleep(2_000);
				this.hitedSuccess=hitedSuccess;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				readWriteLock.writeLock().unlock();
			}
		}
		
		public boolean see(String name){
			readWriteLock.readLock().lock();
			try {
				return this.hitedSuccess;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				readWriteLock.readLock().unlock();
			}
			System.out.println("走的默认没打中的地方……");
			return false;
		}
		
	}
	

	public static void main(String[] args) throws InterruptedException {

		final Person p=new Person();
		new Thread("无相皇"){
			@Override
			public void run() {
					p.hited("幽灵密探",true);
			}
		}.start();
		
		Thread.sleep(1_000);
		
		System.out.println("幽灵密探被打中了么："+p.see("幽灵密探"));
		
		Thread.sleep(1_000);
		
		System.out.println("幽灵密探被打中了么："+p.see("幽灵密探"));
	}

}
