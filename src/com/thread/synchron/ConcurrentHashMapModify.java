package com.thread.synchron;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapModify {

	public static void main(String[] args) {
		// HashMap<String, String> hashMap = new HashMap<String, String>();
		/**
		 * concurrentHashMap弱一致性迭代器, 換成hashmap強一致性迭代器就會出錯
		 * 
		 * 总结
		 * ConcurrentHashMap的弱一致性主要是为了提升效率，是一致性与效率之间的一种权衡。要成为强一致性，就得到处使用锁，甚至是全局锁，这就与Hashtable和同步的HashMap一样了。
		 */
		ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<String, String>();
		hashMap.put("t1", "test1");
		hashMap.put("t2", "test2");
		hashMap.put("t3", "test3");
		hashMap.put("t4", "test4");

		IteratorMap itMap = new IteratorMap(hashMap);
		Thread thread = new Thread(itMap);

		Collection<String> valus = hashMap.values();
		Iterator<String> it = valus.iterator();
		thread.start();
		for (; it.hasNext();) {

			System.out.println(it.next());
		}
	}
}

class IteratorMap implements Runnable {
	private ConcurrentHashMap<String, String> hashMap;

	public IteratorMap(ConcurrentHashMap<String, String> hashMap) {
		this.hashMap = hashMap;
	}

	public void run() {
		// hashMap.put("t5", "test5");
		hashMap.remove("t2");
	}
}
