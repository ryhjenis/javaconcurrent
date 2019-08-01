package com.algorithm;

/**
 * 面试锦集:
 * 
 * 用最简单的方法把数组第一位和第二位， 第二位和第三位，
 * 第三位和第四位， 第四位和第五位，第五位和第六位，
 * 最后一位和第一位更换位置(要求单层循环实现)
 * 
 * @author 小辉GE
 * 
 * 2019年7月30日 下午6:19:00
 */
public class ArrayChangePosition {

	public static void main(String[] args) {
		
		/** 如何使用最简单方法(单层循环) **/
		/**************************** 倒序遍历数组实现方式 ****************************/
		String arr[] = { "1", "4", "5", "6", "7", "8", "9", "10", "11", "15", "156", "169", "155", "19853" };
		// 取得最后的元素
		String tempLast = arr[arr.length - 1];

		for (int i = arr.length - 1; i > 0; i--) {
			// 依次向后赋值
			arr[i] = arr[i - 1];
		}
		// 将最后的元素赋值给第一个位置
		arr[0] = tempLast;

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		// 分割行线
		System.out.println("");
		System.out.println("");

		/**************************** 正序遍历数组实现方式 ****************************/
		String arr_[] = { "1", "4", "5", "6", "7", "8", "9", "10", "11", "15", "156", "169", "155", "19853" };
		String tempLast_ = arr_[arr_.length - 1];// 取得最后的元素
		/** 每次需要上一个元素替代下一个元素前保存要替代的元素 **/
		String tempPrex = "";
		for (int i = 0; i < arr_.length - 1; i++) {
			/** 当前数组遍历是需要替代的下一个元素 **/
			String tempNext = "";
			tempNext = arr_[i + 1];
			if (tempPrex.equals("")) {
				arr_[i + 1] = arr_[i];
			} else {
				arr_[i + 1] = tempPrex;
			}
			tempPrex = tempNext;
		}
		arr_[0] = tempLast_;// 最后把最初的最后一个元素放在第一位
		for (int i = 0; i < arr_.length; i++) {
			System.out.print(arr_[i] + " ");
		}

	}
}