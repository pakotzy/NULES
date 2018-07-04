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
public class Group {
	@Parent Key<Faculty> theFaculty;
	@Id String id;

	public Group(Key<Faculty> theFaculty, String id) {
		this.theFaculty = theFaculty;
		this.id = id;
	}
}
