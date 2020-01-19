package com.qupeng.concurrent.day03.part3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏：本人比喻为：检测站
 * 对构造器进行测试
 * @author Peter
 */
public class CyclicBarrierTest04 {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		
		//公司要在北京开董事会，公司总共3个董事
		final CyclicBarrier barrier=new CyclicBarrier(1);//0和-1都试一下
		
		
		System.out.println("主线程执行完毕！");
	}

}
