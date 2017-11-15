package com.thread.synchron;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题 写一个固定容量的同步容器, 同时拥有put和get方法 能够支持2个生成者线程以及10个消费者线程的阻塞调用
 * 使用lock和condition来实现 可以精确的锁定需要唤醒的线程(例如唤醒消费者线程/生产者线程)
 * 
 * @author raoyanhui
 *
 */
public class ReentrantConditionTest<T> {
	// 容器
	private final LinkedList<T> lists = new LinkedList<T>();
	// 容器最大size为10
	private final int CONTAINER_MAX = 10;
	// 当前容器size
	private int count = 0;

	// lock
	private Lock lock = new ReentrantLock();
	// 生产者
	private Condition producer = lock.newCondition();
	// 消费者
	private Condition consumer = lock.newCondition();

	public void put(T t) {
		lock.lock();
		try {
			while (lists.size() == CONTAINER_MAX) { // 认真考虑为什么不用if要用while
				producer.await();
			}
			lists.add(t);
			++count;
			consumer.signalAll();// 通知其他消费者线程消费
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public T get() {
		T t = null;
		lock.lock();
		try {
			while (lists.size() == 0) { // 认真考虑为什么不用if要用while
				consumer.await();
			}
			t = lists.removeFirst();
			--count;
			producer.signalAll();// 通知生产者线程生成
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args) {
		ReentrantConditionTest<String> syn = new ReentrantConditionTest<String>();
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
