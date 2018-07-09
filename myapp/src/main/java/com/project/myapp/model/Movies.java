package com.project.myapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movies {
	
	@Id
	public ObjectId _id;	
	public String name;
	public String language;
	public String country;

	public Movies() {
		
	}

	public Movies(ObjectId _id, String name, String language, String country) {
		
		this._id = _id;
		this.name = name;
		this.language = language;
		this.country = country;
		
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
