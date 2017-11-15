package com.thread.synchron;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题 写一个固定容量的同步容器, 同时拥有put和get方法 能够支持2个生成者线程以及10个消费者线程的阻塞调用
 * 使用wait和notify/notifyAll来实现
 * 
 * @author raoyanhui
 *
 */
public class SynchronizedTest<T> {
	// 容器
	private final LinkedList<T> lists = new LinkedList<T>();
	// 容器最大size为10
	private final int CONTAINER_MAX = 10;
	// 当前容器size
	private int count = 0;

	public synchronized void put(T t) {
		while (lists.size() == CONTAINER_MAX) { // 认真考虑为什么不用if要用while
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lists.add(t);
		++count;
		this.notifyAll();// 通知其他消费者线程消费
	}

	public synchronized T get() {
		T t = null;
		while (lists.size() == 0) { // 认真考虑为什么不用if要用while
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		--count;
		this.notifyAll();// 通知生产者线程生成
		return t;
	}

	public static void main(String[] args) {
		SynchronizedTest<String> syn = new SynchronizedTest<String>();
		// 启动消费者线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(syn.get());
				}
			}, "consumer" + i).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 启动生产者线程
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					syn.put(Thread.currentThread().getName() + " " + j);
				}
			}, "producer" + i).start();
		}
	}
}
