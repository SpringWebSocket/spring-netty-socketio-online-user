package com.phearun.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getAuthority() {
		return "ROLE_" + name.toUpperCase();
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
