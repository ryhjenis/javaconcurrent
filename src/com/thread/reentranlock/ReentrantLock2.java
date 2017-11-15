package com.thread.reentranlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
	Lock lock = new ReentrantLock();

	void m1() {
		lock.lock();
		try {
			System.out.println("m1=====");
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 使用trylock进行尝试锁定．不管锁定与否，方法都将继续执行 可以根据trylock返回值判断是否锁定
	 * 也可以指定trylock的时间，由于trylock(time)抛出异常，所以注意unlock的处理必须放到finally里面
	 **/
	void m2() {
		// boolean locked = lock.tryLock();
		// System.out.println("m2=====" + locked);
		// if (locked)
		// lock.unlock();

		boolean locked = false;
		try {
			locked = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("m2=====" + locked);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (locked)
				lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLock2 r1 = new ReentrantLock2();
		// 采用ReentrantLock实现同步机制,必须手动关闭锁
		new Thread(r1::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(r1::m2).start();

	}
}
