package com.thread.synchron;

public class DeadLock {
	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();
		Runner r1 = new Runner(1, o1, o2, 5000);
		Runner r2 = new Runner(2, o2, o1, 5000);
		new Thread(r1, "r1").start();
		new Thread(r2, "r2").start();
	}
}

/*
 * sleep:它使得线程在指定的时间内进入阻塞状态，不能得到CPU  时间,把执行机会给其他线程，但是监控状态依然保持,不会 释放对象锁的.
 * 
 * 导致死锁的根源在于不适当地运用“synchronized”关键词来管理线程对特定对象的访问。“synchronized”关键词的作用是，确保在某
 * 个时刻只有一个线程被允许执行特定的代码块，因此，被允许执行的线程首先必须拥有对变量或对象的排他性访问权。当线程访问对象时，线程会给对象加锁，而这
 * 个锁导致其它也想访问同一对象的线程被阻塞，直至第一个线程释放它加在对象上的锁。
 * 
 * 
 * 一般造成死锁必须同时满足如下4个条件: 互斥条件 线程使用的资源必须至少有一个是不能共享的。 请求与保持条件
 * 至少有一个线程必须持有一个资源并且正在等待获取一个当前被其他线程持有的资源。    非剥夺条件 分配的资源不能从相应的线程中被强制剥夺。 循环等待条件
 * 第一个线程等待其他线程，后者又在等待第一个线程。 因为要发生死锁，这4个条件必须同时满足，所以要防止死锁的话，只需要破坏其中一个条件即
 */
class Runner implements Runnable {
	private int id;
	private Object o1;
	private Object o2;
	private int sleepTime;

	public Runner(int id, Object o1, Object o2, int sleepTime) {
		this.id = id;
		this.o1 = o1;
		this.o2 = o2;
		this.sleepTime = sleepTime;
	}

	public void run() {
		System.out.println(id);
		// 锁定资源1
		synchronized (o1) {
			try {
				Thread.sleep(sleepTime);//  这里主要是放大效果，让其他线程获取到时间片  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 锁定资源2
			synchronized (o2) {
				System.out.println();
			}
		}
	}
}