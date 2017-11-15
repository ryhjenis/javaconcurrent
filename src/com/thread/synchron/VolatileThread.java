package com.thread.synchron;

import java.util.concurrent.TimeUnit;

public class VolatileThread {
	// 面试一般会拿volatile 和 synchroized比较, 有整理相关文档
	// volatile保证多个线程之间变量的可见性 , volatile只能保证数据可见性,不能保证数据的原子性
	/* volatile */ boolean running = true;

	void m() {
		System.out.println("m start");
		while (running) {

		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		VolatileThread t = new VolatileThread();
		// jdk1.8新特性 lambda
		// new Thread(()->t.m(), "t1").start();

		new Thread(t::m, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t.running = false;
	}

}
