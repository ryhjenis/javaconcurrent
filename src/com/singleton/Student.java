/**
 * 
 */
package com.singleton;

import java.io.Serializable;

/**
 * @author raoyanhui
 *
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 3096488573300737476L;

	private Integer id;
	private String name;

	public Student() {

	}

	public Student(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		Student s = (Student) obj;
		if (id.equals(s.getId())) {
			return true;
		}
		return false;

	}

}
