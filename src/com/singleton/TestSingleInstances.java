package com.singleton;

public class TestSingleInstances {

	public static void main(String[] args) {

		Singleton si1 = Singleton.getInstance();
		Singleton si2 = Singleton.getInstance();
		System.out.println(si1.toString() + "===" + si2.toString());
		if (si1 == si2) {
			System.out.println("同一对象");
		} else {
			System.out.println("非同一对象");
		}

		for (int i = 0; i < 20; i++) {
			new MyThread().start();
		}

	}

}

class MyThread extends Thread {
	public void run() {
		Singleton sing = Singleton.getInstance();
		System.out.println(sing.toString());
	}

}
