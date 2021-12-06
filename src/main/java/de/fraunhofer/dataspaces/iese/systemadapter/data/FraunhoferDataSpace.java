package de.fraunhofer.dataspaces.iese.systemadapter.data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * This class is used as a container data field in payload models
 */
public class FraunhoferDataSpace {
	
	private String name;
	
	private String duration;
	
	private String type;
	
	private String date;

	public String getName() {
		return name;
	}

	public FraunhoferDataSpace setName(String name) {
		this.name = name;
		
		return this;
	}

	public String getDuration() {
		return duration;
	}

	public FraunhoferDataSpace setDuration(String duration) {
		this.duration = duration;
		
		return this;
	}

	public String getType() {
		return type;
	}

	public FraunhoferDataSpace setType(String type) {
		this.type = type;
		return this;
	}
	

	public String getDate() {
		return date;
	}

	public void setDate(LocalDateTime  date) {
		this.date = Long.toString(date.toEpochSecond(ZoneOffset.UTC));
	}

	@Override
	public String toString() {
		return "FraunhoferDataSpace [name=" + name + ", duration=" + duration + ", type=" + type + "]";
	}
	
}
