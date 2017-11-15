package com.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer3 {
	volatile List<Object> lists = new ArrayList<Object>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int getSize() {
		return lists.size();
	}

	public static void main(String[] args) {
		MyContainer3 contain = new MyContainer3();
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
			}
		}, "线程2").start();

		new Thread(() -> {
			synchronized (obj) {
				System.out.println("线程1启动");
				for (int i = 0; i < 10; i++) {
					contain.add(new Object());
					System.out.println("添加到第: " + (i + 1) + "元素");
					if (contain.getSize() == 5) {
						// notify 不会释放锁
						obj.notify();
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