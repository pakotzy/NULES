package com.pako.nules.server.db;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

import java.util.Date;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017.
 */

@Entity
public class Message {
	@Parent Key<System> theSystem;
	@Id public Long id;
	@Load Ref<Teacher> sender;
	Key<Group> groupRecieverKey;
	Key<Faculty> facultyRecieverKey;
	String header;
	String body;
	Date date;
	Date creation_date;

	public Message(Key<System> theSystem, Long id, Ref<Teacher> sender, Key<Group> groupRecieverKey, Key<Faculty> facultyRecieverKey, String header, String body, Date date, Date creation_date) {
		this.theSystem = theSystem;
		this.id = date.getTime();
		this.sender = sender;
		this.groupRecieverKey = groupRecieverKey;
		this.facultyRecieverKey = facultyRecieverKey;
		this.header = header;
		this.body = body;
		this.date = date;
		this.creation_date = new Date();
	}
}
