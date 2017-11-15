package com.thread.synchron;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

	/*
	 * 已整理文档:
	 * 
	 * LinkedBlockingQueue的put,add和offer的区别       
	 * 最近在学习<<Java并发编程实践>>，有很多java.util.concurrent包下的新类。
	 * LinkedBlockingQueue就是其中之一，顾名思义这是一个阻塞的线程安全的队列，底层应该采用链表实现。      
	 *  看其API的时候发现，添加元素的方法竟然有三个：add,put,offer。
	 * 且这三个元素都是向队列尾部添加元素的意思。于是我产生了兴趣，要仔细探究一下他们之间的差别。 1.首先看一下add方法
	 * LinkedBlockingQueue构造的时候若没有指定大小，则默认大小为Integer.MAX_VALUE，
	 * 当然也可以在构造函数的参数中指定大小。LinkedBlockingQueue不接受null。      
	 *  add方法在添加元素的时候，若超出了度列的长度会直接抛出异常：
	 * 
	 * 2.再来看一下put方法     对于put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素。
	 * 
	 * 3.最后看一下offer方法 offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false。  
	 * 
	 * poll:若队列为空，返回null
	 * 
	 * remove:若队列为空，抛出NoSuchElementException异常。
	 * 
	 * take:若队列为空，发生阻塞，等待有元素。
	 */

	/**
	 * 测试发现, 当调用wait方法后, 线程处于等待, 在次唤醒后, 代码继续向下执行, 而不是从新开始
	 * 
	 * * Obj.wait(),Obj.notify必须在synchronized(Obj){...}语句块内。
	 * 从功能上来说wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠。
	 * 直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行。
	 * 相应的notify()就是对对象锁的唤醒操作。但有一点需要注意的是notify()调用后，
	 * 并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageSender ms = new MessageSender();
		try {
			new Thread(ms).start();

			for (int i = 0; i < 2; i++) {
				ms.send("" + i);
			}

			Thread.sleep(3000);

			ms.send("" + 3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ms.stop();
		}
	}

}

class MessageSender implements Runnable {

	protected boolean running = true;
	protected final Queue<String> currentLinkedQueue = new ConcurrentLinkedQueue<String>();

	public MessageSender() {

	}

	public void stop() {
		running = false;
		synchronized (currentLinkedQueue) {
			currentLinkedQueue.notify();
		}
	}

	public void send(String message) {
		currentLinkedQueue.add("[" + message + "]: " + message);

		synchronized (currentLinkedQueue) {
			currentLinkedQueue.notify();
			try {
				Thread.sleep(3000);
				System.out.println("currentLinkedQueue 调用send方法唤醒之后发生");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * The background thread that listens for incoming TCP/IP connections and
	 * hands them off to an appropriate processor.
	 */
	@Override
	public void run() {
		// Loop until we receive a shutdown command
		while (running) {
			synchronized (currentLinkedQueue) {
				System.out.println("-----进入run方法------");
				try {
					System.out.println("-----进入try方法------");
					if (currentLinkedQueue.isEmpty()) {
						System.out.println("-----进入wait方法------");
						currentLinkedQueue.wait();
						System.out.println("-----调用wait方法后,外部代码是否继续执行1------");
					}
				} catch (InterruptedException e) {
					// Ignore
				}
				System.out.println("-----调用wait方法后,外部代码是否继续执行2------");
				System.out.println(currentLinkedQueue.poll());
			}
		}

	}

}