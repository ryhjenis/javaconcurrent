package com.thread.synchron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	// atomicxxx原子性 (atomicxxx多个原子性方法同时用到,如不加synchroized,还是不能构成原子性)
	AtomicInteger count = new AtomicInteger();

	void m() {
		for (int i = 0; i < 1000; i++) {
			count.incrementAndGet();// 替代count++
		}
	}

	public static void main(String[] args) {
		AtomicTest t = new AtomicTest();

		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread- " + i));
		}
		threads.forEach((o) -> o.start());
		// join是线程顺序执行
		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(t.count);
	}

}
