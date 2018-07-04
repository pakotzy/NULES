package com.pako.nules.server.db;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/23/2017.
 */

@Entity
public class Teacher implements Serializable {
	@Parent Key<System> theSystem;
	@Id public String id;
	@Index public String tKey;
	Key<Faculty> facultyKey;
	public String firstName;
	public String secondName;
	public String lastName;

	public Teacher(Key<System> theSystem, String id, String tKey, Key<Faculty> facultyKey, String firstName, String secondName, String lastName) {
		this.theSystem = theSystem;
		this.id = id;
		this.tKey = tKey;
		this.facultyKey = facultyKey;
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
	}
}
