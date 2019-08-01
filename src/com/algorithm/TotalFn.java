package com.algorithm;

/**
 * 多少天铺满整个池塘
 * // f(0)=0; f(1)=1;
 * // f(n)=f(n-1)+f(n-2)
 * 
 * @author 小辉哥/小辉GE
 *
 * 2019年8月1日 下午3:43:18
 *
 */
public class TotalFn {

	public static void main(String[] args) {
		
		System.out.println(getSpeTotalSum(5));
	}

	public static int getTotalSum(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return getTotalSum(n - 1) + getTotalSum(n - 2);
		}
	}

	/**
	 * 1,2,4,8,18.......第二十八天铺位整个池塘
	 * 
	 * @param n
	 * @return
	 */
	public static int getSpecialTotalSum(int n) {
		if (n == 0)
			return 0;
		else {
			return (int) Math.pow(2, (n - 1)) + getSpecialTotalSum(n - 1);
		}

	}

	public static int getSpeTotalSum(int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;
		return n * getSpeTotalSum(n - 1);
	}

}
