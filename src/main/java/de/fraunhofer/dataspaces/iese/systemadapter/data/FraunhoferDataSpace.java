package de.fraunhofer.dataspaces.iese.systemadapter.data;

public class FraunhoferDataSpace {
	
	private String name;
	
	private String duration;
	
	private String type;

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

	@Override
	public String toString() {
		return "FraunhoferDataSpace [name=" + name + ", duration=" + duration + ", type=" + type + "]";
	}
	
	
}
