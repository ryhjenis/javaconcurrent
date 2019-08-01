package com.algorithm;

/**
 * 面试锦集:
 * 
 * 实现方法int findNextPrime(int i)，使其返回下一个大于i的质数。
 * 
 * 百度百科对质数的定义：质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。
 * 
 * @author 小辉GE/小辉哥
 * 
 * 2019年7月31日 下午6:20:00
 */
public class FindNextPrimenumber {
	// 方法判断是否是质数
	public static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	// 假设i+j为下一个大于i的质数
	public static int findNextPrime(int i) {
		// 质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数，因此这里不考虑参数i小于1的情况
		if (i < 1)
			return -1;
		//temp为i到下一个可能存在的质数增量
		int temp = 0;
		// 定义是否存在该质数的变量
		boolean isPrime = false;
		// 开始逻辑处理
		for (int j = 1; j < Integer.MAX_VALUE; j++) {
			if (isPrime(i + j)) {
				isPrime = true;
				temp = j;
				break;
			}
		}
		// 如果逻辑判断后不存在该质数
		if (!isPrime) {
			return -1;
		}
		return i + temp;
	}

	public static void main(String[] args) {
		int i = 63;
		int result = findNextPrime(i);
		if(!(result == -1)){
			System.out.println("存在下一个大于"+i+"的质数，该质数为：" + result);
		}else{
			System.out.println("抱歉不存在下一个大于"+i+"的质数");
		}
	}
}
