package com.reflect;

public class ReflectInstance {

	public static void main(String[] args) {
		try {
			// 应该是class的对象Class.forName("全限路径");
			Parent p = (Parent) Class.forName("com.reflect.Parent").newInstance();

			Parent pp = Parent.class.newInstance();

			System.out.println(p != null);

			System.out.println(pp != null);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
