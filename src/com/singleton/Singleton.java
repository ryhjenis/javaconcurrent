package com.singleton;

public class Singleton {
	private static Singleton single = null;

	private Singleton() {

	}

	/*
	 * public synchronized static Singleton getInstance() { if (single == null)
	 * { single = new Singleton(); } return single; }
	 */

	public static Singleton getInstance() {
		if (single == null) {
			synchronized (Singleton.class) {
				if (single == null) {
					single = new Singleton();
				}
			}
		}
		return single;
	}
}

/*
 * public class Singleton {       private Singleton instance = null;  
 *     static {       instance = new Singleton();       }  
 *     private Singleton (){}       public static Singleton getInstance() {  
 *     return this.instance;       }   }  
 */

/*
 * public class Singleton {  
 *     private static final Singleton instance = new Singleton();  
 *     private Singleton (){}       public static Singleton getInstance() {  
 *     return instance;       }   }  
 */