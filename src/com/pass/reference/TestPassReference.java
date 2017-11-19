package com.pass.reference;

public class TestPassReference {

	public static void main(String[] args) {
		BigMan bigMan = new BigMan();
		bigMan.setId(1);
		bigMan.setName("张三");
		System.out.println(bigMan.getName());

		People people = new People(bigMan);
		people.handleAnimalName();

		System.out.println(bigMan.getName());
		System.out.println(bigMan.getName());

	}
}
