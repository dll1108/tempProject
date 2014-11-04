package com.example.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {
	private String Bname;
	private String Aname;
	private String Dname;
	private int image;
	
	public Book() {
		super();
	}

	public Book(String bname, String aname, String dname, int image) {
		super();
		Bname = bname;
		Aname = aname;
		Dname = dname;
		this.image = image;
	}
	
	public String getBname() {
		return Bname;
	}

	public void setBname(String bname) {
		Bname = bname;
	}

	public String getAname() {
		return Aname;
	}

	public void setAname(String aname) {
		Aname = aname;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}



}
