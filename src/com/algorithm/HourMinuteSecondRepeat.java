package com.algorithm;

/**
 * 面试题一天24小时时分秒重合次数
 * 
 * @author RAOYANHUI
 * 
 */
public class HourMinuteSecondRepeat {

	public static void main(String[] args) {
		// 总共时间数
		int totalSecond = 24 * 60 * 60;
		// 时针速度、分针速度、秒针速度(速度放大了720倍,按照每走一刻度需要的时间来算的(时种都是划分60个刻度))
		int hourV = 1, minuteV = 12, secondV = 720;
		// 时针、分针、秒针对应时间刻度总共走的长度
		int hourLength = 0, minuteLength = 0, secodLength = 0;
		// 时钟周长((秒针的速度*运行一周时间)/720, 周长不用放大)
		int circle = 60;

		// 记录12小时总共重合的次数
		int times = 0;

		// 总共的时间范围
		for (int i = 1; i <= totalSecond; i++) {
			hourLength = hourV * i;
			minuteLength = minuteV * i;
			secodLength = secondV * i;

			// 需要考虑可能会出现分钟、秒针已走完一圈或多圈情况，不能直接用长度相等比较，应该用取模比较
			if ((hourLength / 720) % circle == (minuteLength / 720) % circle
					&& (hourLength / 720) % circle == (secodLength / 720) % circle) {
				times++;
				int h = i / 3600;
				int m = (i - 3600 * h) / 60;
				int s = i - 3600 * h - 60 * m;
				System.out.println(h + ":" + m + ":" + s);
			}

		}

		System.out.println("时针、分针、秒针一天共有" + times + "次重合！");

	}
}
