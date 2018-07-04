package com.pako.nules.client.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017
 */

@Entity
public class Settings {
	@PrimaryKey public int id = 0;
	public String name;
	public String second_name;
	public String group;
	public String key;
	public Boolean notifications = false;

	public Settings(){}

	@Ignore
	public Settings(String name, String second_name, String group, String key, Boolean notifications) {
		this.name = name;
		this.second_name = second_name;
		this.group = group;
		this.key = key;
		this.notifications = notifications;
	}
}
