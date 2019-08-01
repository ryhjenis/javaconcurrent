package com.algorithm;

/**
 * 
 * @author 小辉哥/小辉GE
 *
 * 2019年8月1日 下午3:45:27
 *
 */
public class TestTrims2 {
	public static void main(String[] args) {
		/**
		 * 测试发现 char[i]=' '是空格 char[i]<' '是tab键空格
		 * 因此过滤用char[i]<= ' ' 或者  char[i]> ' '
		 */
		String str = new String("   		ac     			  a 		bc 		  ssa	   ");
		System.out.println(str.replaceAll("\\s+", ""));
		System.out.println(trims(str));
		System.out.println(trims1(str));
		System.out.println(trims2(str));
	}

	/**
	 * 去除空格trim函数(只能去除两头空格, 这里是String类自己的trim函数)
	 * 
	 * @param args
	 * @return
	 */
	public static String trims(String args) {
		char[] value = args.toCharArray();
		int len = value.length;
		int st = 0;
		char[] val = value;

		while ((st < len) && (val[st] <= ' ')) {
			st++;
		}

		while ((st < len) && (val[len - 1] <= ' ')) {
			len--;
		}
		return ((st > 0) || (len < value.length)) ? args.substring(st, len) : args;
	}

	/**
	 * 自定义去除中间空格+两头空格trims1函数
	 * 
	 * @param args
	 * @return
	 */
	public static String trims1(String args) {
		char[] value = args.toCharArray();
		int len = value.length;
		char res[] = null;
		int reslen = 0;
		int index = 0;
		for (int i = 0; i < len; i++) {
			// 非空格的字符数
			if (value[i] > ' ') {
				reslen++;
			}
		}
		res = new char[reslen];
		for (int i = 0; i < len; i++) {
			// 非空格的字符数
			if (value[i] > ' ') {
				res[index] = value[i];
				index++;
			}
		}
		return (index > 0) ? new String(res) : args;
	}

	/**
	 * 自定义去除中间空格+两头空格trims2函数
	 * 
	 * @param args
	 * @return
	 */
	public static String trims2(String args) {
		StringBuilder strbuilder = new StringBuilder();
		char[] value = args.toCharArray();
		int len = value.length;
		int index = 0;
		for (int i = 0; i < len; i++) {
			// 非空格的字符数
			if (value[i] > ' ') {
				strbuilder.append(value[i]);
				index++;
			}
		}
		return (index > 0) ? strbuilder.toString() : args;
	}
}
