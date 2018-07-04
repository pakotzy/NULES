package com.pako.nules.server.pojos;

import java.io.Serializable;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/24/2017.
 */
public class Teacher implements Serializable {
	public String id;
	private String tKey;
	public String facultyKey;
	public String firstName;
	public String secondName;
	public String lastName;

	public Teacher(String id, String tKey, String facultyKey, String firstName, String secondName, String lastName) {
		this.id = id;
		this.tKey = tKey;
		this.facultyKey = facultyKey;
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
	}
}
