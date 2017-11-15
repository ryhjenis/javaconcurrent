package com.thread.reentranlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock3 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Thread t1 = new Thread(() -> {
			lock.lock();
			try {
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println("t1 end");
			} catch (InterruptedException e) {
				System.out.println("interrupt");
			} finally {
				lock.unlock();
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			// lock.lock();
			boolean locked = false;
			try {
				lock.lockInterruptibly(); // 可以对interrupt方法做出响应(代替lock锁机制,可以被打断)
				locked = lock.tryLock();
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				System.out.println("interrupt");
			} finally {
				if (locked)
					lock.unlock();
			}
		});
		t2.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t2.interrupt();// 打断线程二的等待
	}
}
