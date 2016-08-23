package org.tux2586.noticeBoard.beans;

public class Location {
//	private String county;
	private String city;
	private String state;
//	private String country;

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String toString(){
		return city + ", " +state;
	}

}
