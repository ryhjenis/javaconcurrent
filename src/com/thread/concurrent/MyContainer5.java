package com.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer5 {
	volatile List<Object> lists = new ArrayList<Object>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int getSize() {
		return lists.size();
	}

	public static void main(String[] args) {
		MyContainer5 contain = new MyContainer5();
		// 门闩
		CountDownLatch latch = new CountDownLatch(1);

		new Thread(() -> {
			System.out.println("线程2启动");
			if (contain.getSize() != 5) {
				try {
					// 进入等待
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("线程2结束");
		}, "线程2").start();

		new Thread(() -> {
			System.out.println("线程1启动");
			for (int i = 0; i < 10; i++) {
				contain.add(new Object());
				System.out.println("添加到第: " + (i + 1) + "元素");

				if (contain.getSize() == 5) {
					// 打开门闩,让另一线程可以执行(自己也会继续执行,效率是更高)
					latch.countDown();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("线程1结束");
		}, "线程1").start();

	}
}