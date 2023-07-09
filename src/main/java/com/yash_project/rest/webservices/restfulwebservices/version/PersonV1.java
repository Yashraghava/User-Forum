package com.yash_project.rest.webservices.restfulwebservices.version;

public class PersonV1 {
	private String name;

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV1 [name=" + name + "]";
	}

	public String getName() {
		return name;
	}
	

}
