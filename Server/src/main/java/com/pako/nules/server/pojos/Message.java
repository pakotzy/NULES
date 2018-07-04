package com.pako.nules.server.pojos;

import java.util.Date;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/9/2017.
 */

public class Message {
	public Long id;
	public Teacher sender;
	public String groupRecieverKey;
	public String facultyRecieverKey;
	public String header;
	public String body;
	public Date date;
	public Date creation_date;

	public Message(Long id, Teacher sender, String groupRecieverKey, String facultyRecieverKey, String header, String body, Date date, Date creation_date) {
		this.id = id;
		this.sender = sender;
		this.groupRecieverKey = groupRecieverKey;
		this.facultyRecieverKey = facultyRecieverKey;
		this.header = header;
		this.body = body;
		this.date = date;
		this.creation_date = creation_date;
	}
}