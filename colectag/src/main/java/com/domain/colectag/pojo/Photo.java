package com.domain.colectag.pojo;

import java.io.Serializable;

public class Photo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String tag;
	private String owner;
	private String secret;
	private Integer server;
	private String title;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public Integer getServer() {
		return server;
	}
	public void setServer(Integer server) {
		this.server = server;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
