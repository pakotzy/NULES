package com.pako.nules.server.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/24/2017.
 */

public class Schedule implements Serializable {
	public String id;
	public List<Subject> subjects = new ArrayList<>();
	String groupKey;
	Date date;

	public Schedule(String id, List<Subject> subjects, String groupKey, Date date) {
		this.id = id;
		this.subjects = subjects;
		this.groupKey = groupKey;
		this.date = date;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}
