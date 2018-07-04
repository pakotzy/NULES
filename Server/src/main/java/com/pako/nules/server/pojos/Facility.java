package com.pako.nules.server.pojos;

import java.io.Serializable;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/24/2017.
 */
public class Facility implements Serializable {
	public Long id;
	public String name;
	public Double latitude, longitude;

	public Facility(Long id, String name, Double latitude, Double longitude) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
