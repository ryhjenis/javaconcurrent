package com.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer2 {
	volatile List<Object> lists = new ArrayList<Object>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int getSize() {
		return lists.size();
	}

	public static void main(String[] args) {
		MyContainer2 contain = new MyContainer2();

		new Thread(() -> {
			System.out.println("线程1启动");
			for (int i = 0; i < 10; i++) {
				contain.add(new Object());
				System.out.println("添加到第: " + (i + 1) + "元素");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("线程1结束");
		}, "线程1").start();

		new Thread(() -> {
			System.out.println("线程2启动");
			while (true) {
				if (contain.getSize() == 5) {
					break;
				}
			}
			System.out.println("线程2结束");
		}, "线程2").start();
	}

}
