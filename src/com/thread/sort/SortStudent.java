package com.thread.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thread.singleton.Student;

public class SortStudent {
	public static void main(String[] args) throws Exception {

		Map<String, Student> myMap = new LinkedHashMap<String, Student>();
		myMap.put("1", new Student(1, "aa"));
		myMap.put("2", new Student(4, "bb"));
		myMap.put("3", new Student(3, "cc"));
		myMap.put("4", new Student(9, "dd"));
		myMap.put("5", new Student(6, "ee"));
		myMap.put("6", new Student(3, "ff"));

		myMap = sortMap(myMap);

		printlnMap(myMap);

	}

	public static void printlnMap(Map<String, Student> newMap) {
		for (Iterator<Entry<String, Student>> it = newMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Student> entry = (Map.Entry<String, Student>) it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static Map<String, Student> sortMap(Map<String, Student> oldMap) {
		ArrayList<Map.Entry<String, Student>> list = new ArrayList<Map.Entry<String, Student>>(oldMap.entrySet());

		// 新特性写法利用list的sort方法
		// list.sort((o1, o2) ->
		// o1.getValue().getId().compareTo(o2.getValue().getId()));

		// list.sort((o1, o2) -> {
		// return o1.getValue().getId().compareTo(o2.getValue().getId());
		// });

		// 改写Collections.sort方法用jdk1.8新特性
		// Collections.sort(list, (o1, o2) ->
		// o1.getValue().getId().compareTo(o2.getValue().getId()));

		// Collections.sort(list, (o1, o2) -> {
		// return o1.getValue().getId().compareTo(o2.getValue().getId());
		// });

		Collections.sort(list, new Comparator<Map.Entry<String, Student>>() {
			@Override
			public int compare(Entry<java.lang.String, Student> arg0, Entry<java.lang.String, Student> arg1) {
				return arg0.getValue().getId() - arg1.getValue().getId();
			}
		});
		Map<String, Student> newMap = new LinkedHashMap<String, Student>();
		for (int i = 0; i < list.size(); i++) {
			newMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return newMap;
	}
}
