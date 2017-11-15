package com.thread.reentranlock;

import java.util.concurrent.locks.ReentrantLock;

//比较ReentrantLock 和 synchronized区别
// ReentrantLock 必须手动释放锁(ReentrantLock比synchronized更灵活)
// 使用ReentrantLock可以尝试锁定 trylock, 这样无法锁定或在指定时间内无法锁定, 线程可以决定是否继续等待
// 使用ReentrantLock还可以调用lockInterruptibly方法, 可以对线程interrupt方法做成响应, 在一个线程等待锁的过程中可以被打断
//　使用ReentrantLock可以指定公平锁Lock lock = new ReentrantLock(true);

public class ReentrantLock4 /* extends Thread */ {
	private static ReentrantLock lock = new ReentrantLock(true);// true
																// 表示指定公平锁,可以对比输出结果

	public void m() {
		for (int i = 0; i < 100; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "获得锁");
			} finally {
				lock.unlock();
			}
		}

	}

	public static void main(String[] args) {
		// ReentrantLock4 rl = new ReentrantLock4();
		// Thread t1 = new Thread(rl);
		// Thread t2 = new Thread(rl);
		//
		// t1.start();
		// t2.start();
		ReentrantLock4 rl = new ReentrantLock4();
		new Thread(rl::m, "t1").start();
		new Thread(rl::m, "t2").start();

	}
}
