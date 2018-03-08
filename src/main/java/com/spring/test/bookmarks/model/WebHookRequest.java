package com.spring.test.bookmarks.model;

import java.util.List;

public class WebHookRequest {
	
	private String  object;
	private List<Messaging> entry;
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public List<Messaging> getEntry() {
		return entry;
	}
	public void setEntry(List<Messaging> entry) {
		this.entry = entry;
	}

}
