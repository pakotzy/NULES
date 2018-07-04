package com.pako.nules.server.pojos;

import java.io.Serializable;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/24/2017.
 */
public class Subject implements Serializable {
	public String theSchedule;
	public String id;
	public String name;
	public Integer room;
	public Teacher teacher;
	public Facility facility;

	public Subject(String theSchedule, String id, String name, Integer room, Teacher teacher, Facility facility) {
		this.theSchedule = theSchedule;
		this.id = id;
		this.name = name;
		this.room = room;
		this.teacher = teacher;
		this.facility = facility;
	}
}
