package com.bawei.viewpager.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

public class GetSDcardImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Bitmap img;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bitmap getImg() {
		return img;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	public GetSDcardImage() {
		super();
	}
	
	public GetSDcardImage(Bitmap img) {
		super();
		this.img = img;
	}
	public GetSDcardImage(int id, Bitmap img) {
		super();
		this.id = id;
		this.img = img;
	}
	@Override
	public String toString() {
		return "GetSDcardImage [id=" + id + ", img=" + img + "]";
	}
	
}
