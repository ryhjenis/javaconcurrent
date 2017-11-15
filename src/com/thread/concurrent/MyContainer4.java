package com.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer4 {
	volatile List<Object> lists = new ArrayList<Object>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int getSize() {
		return lists.size();
	}

	public static void main(String[] args) {
		MyContainer4 contain = new MyContainer4();
		final Object obj = new Object();

		new Thread(() -> {
			synchronized (obj) {
				System.out.println("线程2启动");
				if (contain.getSize() != 5) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("线程2结束");
				obj.notify();
			}
		}, "线程2").start();

		new Thread(() -> {
			synchronized (obj) {
				System.out.println("线程1启动");
				for (int i = 0; i < 10; i++) {
					contain.add(new Object());
					System.out.println("添加到第: " + (i + 1) + "元素");

					if (contain.getSize() == 5) {
						obj.notify();// notify唤醒其他线程但是不会释放锁
						try {
							// 释放锁,才能让其他线程执行,这里是让此线程的obj锁释放
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("线程1结束");
			}
		}, "线程1").start();

	}
}