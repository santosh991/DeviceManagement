package com.smart.school.devicemanagement.models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ImageInfo")
public class ImageInfo implements java.io.Serializable{

	private String pk;
	private byte[] image;
	
	public ImageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ImageInfo(String pk, byte[] image) {
		super();
		this.pk = pk;
		this.image = image;
	}
	
	@Id
	@Column(name = "pk",  nullable = false, length = 36)
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	
	@Lob 
	@Column(name="image", columnDefinition="BLOB", nullable=true) 
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
