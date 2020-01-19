package com.qupeng.concurrent.day03.part2;

/**
 *LockSupport的等待是否释放锁
 * @author Peter
 */
public class LockSupportTest03 {
	
	private synchronized static void testSync() throws InterruptedException{
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName+"：进入同步方法……");
		System.out.println(threadName+"：开始等待……");
		for(int i=1;i<=3;i++){
			//sleep不释放锁
			System.out.println(threadName+"：现在是在同步方法中的等待的第"+i+"秒");
			Thread.sleep(1_000);
			
			/*//wait要释放锁
			LockSupportTest03.class.wait(1_000);
			System.out.println(threadName+"：现在是在同步方法中的第"+i+"秒");*/
			
			/*//LockSupport的park方法不释放锁
			//注意这个参数是纳秒：1秒等于1千毫秒，等于100万微秒，等于10亿纳秒
			LockSupport.parkNanos(1_000_000_000);
			LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));*/
			
			System.out.println(threadName+"：现在是在同步方法中的第"+i+"秒");
		}
		System.out.println(threadName+"：离开同步方法……");
	}
	

	public static void main(String[] args) {
		//开启两个线程去执行同步方法
		for(int i=1;i<=2;i++){
			new Thread("线程"+i){
				public void run() {
					try {
						testSync();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		System.out.println("主线程执行完毕！！！！");
	}
}
