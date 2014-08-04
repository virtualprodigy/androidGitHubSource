package com.virtualprodigy.AndrpodSampleApp;

public class Contact {
private int rowID;
private String firstName;
private String lastName;
private String phone;
private String address;
private String photoPath;

public Contact(int rowID, String firstName, String lastName, String phone,
		String address, String photoPath) {
	super();
	this.rowID = rowID;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.address = address;
	this.photoPath = photoPath;
}

public int getRowID() {
	return rowID;
}

public void setRowID(int rowID) {
	this.rowID = rowID;
}

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhotoPath() {
	return photoPath;
}
public void setPhotoPath(String photoPath) {
	this.photoPath = photoPath;
}

}
