package com.pako.nules.server.db;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.io.Serializable;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/17/2017
 */

@Entity
public class Subject implements Serializable {
	@Parent public Key<Schedule> theSchedule;
	@Id public String id;
	public String name;
	public Integer room;
	@Index@Load public Ref<Teacher> teacher;
	@Load public Ref<Facility> facility;

	public Subject(){}

	public Subject(Key<Schedule> theSchedule, String id, String name, Integer room, Ref<Teacher> teacher, Ref<Facility> facility) {
		this.theSchedule = theSchedule;
		this.id = id;
		this.name = name;
		this.room = room;
		this.teacher = teacher;
		this.facility = facility;
	}
}
