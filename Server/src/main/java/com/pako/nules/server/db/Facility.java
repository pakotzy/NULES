package com.pako.nules.server.db;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/23/2017.
 */

@Entity
public class Facility implements Serializable {
	@Parent Key<System> theSystem;
	@Id public Long id;
	public String name;
	public Double latitude, longitude;

	public Facility(Key<System> theSystem, Long id, String name, Double latitude, Double longitude) {
		this.theSystem = theSystem;
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
