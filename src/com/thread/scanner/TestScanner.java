package com.thread.scanner;

import java.util.Scanner;

public class TestScanner {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		/**
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in)); try {
		 * System.out.println(br.readLine()); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * byte[] bu = new byte[1024]; try { System.in.read(bu);
		 * System.out.println(new String(bu)); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		Scanner sc = new Scanner(System.in);
		System.out.println(sc.nextLine());

	}

}
