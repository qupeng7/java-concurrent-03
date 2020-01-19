package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏：本人比喻为：检测站
 * @author qupeng
 */
public class CyclicBarrierTest01 {

	public static void main(String[] args) throws InterruptedException {
		
		//公司要在北京开董事会，公司总共3个董事
		final CyclicBarrier barrier=new CyclicBarrier(3);
		
		/*final CyclicBarrier barrier=new CyclicBarrier(3,new  Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"所有人已到达北京，召开董事会！");
			}
		});*/
		
		
		//创建3个董事线程
		for(int i=1;i<=3;i++){
			new Thread("董事"+i+"："){
				@Override
				public void run() {
					try {
						String threadName = Thread.currentThread().getName();
						System.out.println(threadName+"出发前往北京……");
						//董事1到达北京需要1秒
						if("董事1：".equals(threadName)){
							Thread.sleep(1000);
						}else if("董事2：".equals(threadName)){//董事2到达北京需要3秒
							Thread.sleep(3000);
						}else{//董事3到达北京需要4秒
							Thread.sleep(4000);
						}
						System.out.println(threadName+"已到达北京！");
						//getNumberWaiting方法，当调用await之后这个数字就会减1
						int waittingNum =barrier.getNumberWaiting();
						System.out.println(threadName+"当前有"+(waittingNum+1)+"个董事到达北京。");
						barrier.await();
						//每一个董事都知道自己到达后自己还需不需要等待
						System.out.println(threadName+"总共需要"+barrier.getParties()+"人才能开董事会！");
						if(waittingNum==0){
							System.out.println(threadName+"所有人已经到达北京，召开董事会！");
						}
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

	}

}
