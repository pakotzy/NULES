package com.pako.nules.server.db;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017.
 */

@Entity
public class Faculty {
	@Parent Key<System> theSystem;
	@Id String id;
	public String name;

	public Faculty(Key<System> theSystem, String id, String name) {
		this.theSystem = theSystem;
		this.id = id;
		this.name = name;
	}
}
