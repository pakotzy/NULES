package com.pako.nules.server.db;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/17/2017
 */

@Entity
public class Schedule implements Serializable {
	@Parent Key<System> theSystem;
	@Id public String id;
	@Load public List<Ref<Subject>> subjects = new ArrayList<>();
	Key<Group> groupKey;
	Date date;

	public Schedule() {
		date = new Date();
	}

	public Schedule(Key<System> theSystem, String id, List<Ref<Subject>> subjects, Key<Group> groupKey, Date date) {
		this();
		this.theSystem = theSystem;
		this.id = id;
		this.subjects = subjects;
		this.groupKey = groupKey;
	}

	public void setSubjects(List<Ref<Subject>> subjects) {
		this.subjects = subjects;
	}
}
