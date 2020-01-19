package com.qupeng.concurrent.day03.lookback;

/**
 * 生产者消费者模式使用nodify的弊端：
 * 有可能会唤醒本类的线程即：
 * 在多生产这多消费者的时候会出现
 * 消费者唤醒消费者，生产者唤醒生产者而的情况。
 * @author qupeng
 */
public class ProducerAndConsumerTest {

	
	public static void main(String[] args) {
		ShareData data=new ShareData();
		
		new Producer2(data,"生产者01").start();
		new Producer2(data,"生产者02").start();
		new Consumer2(data,"消费者01").start();
		new Consumer2(data,"消费者02").start();

	}

}


class  ShareData {
	
	private String  name;
	
	private int count=0;
	
	private int number=1;
	
	private static final int MAX=1;
	
	
	//生产
	public   synchronized void  product(String name) throws InterruptedException{
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"：要开始生产了……");
		//如果等于最大就等待
		while(count==MAX){
			System.out.println(threadName+"：已经达到最大，等着消费者消费……");
			this.wait();
			Thread.sleep(10);
		}
		//如果没有达到最大，就生产
		this.name="第"+number+"号"+name;
		count++;
		number++;
		System.out.println(threadName+"：已经做好了"+this.name+"啦！");
		/*
		 * 做好一个就唤醒消费者进行消费
		 * 注意这里的nodify不一定唤醒的是消费者
		 */
		this.notify();
//		this.notifyAll();
		
	}
	
	
	//消费
	public   synchronized void  consume() throws InterruptedException{
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"：要开始消费了……");
		//如果没有了，就等待
		while(count==0){
			this.wait();
			Thread.sleep(10);
		}
		//如果达到最大就消费
		count--;
		System.out.println(threadName+"：吃掉了"+name);
		/*
		 * 没有了就唤醒生产者可以继续生产了
		 * 但是唤醒的不一定是生产者
		 */
		this.notify();
//		this.notifyAll();
	}
	
}

class  Producer2 extends Thread{
	
	private ShareData  data;
	
	public Producer2(ShareData data,String name) {
		super(name);
		this.data=data;
	}
	
	@Override
	public void run() {
		try {
			//生产30根油条
			for (; ;) {
				data.product("油条");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class  Consumer2 extends Thread{
		private ShareData  data;
		
		public Consumer2(ShareData data,String name) {
			super(name);
			this.data=data;
		}
		
		@Override
		public void run() {
			try {
				//消费10根油条
				for (; ; ) {
					data.consume();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}
