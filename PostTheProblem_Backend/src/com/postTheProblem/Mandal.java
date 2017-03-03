package com.postTheProblem;

public class Mandal {
private int mandal_id;
private int dist_id;
private String mandal_name;
public Mandal(int mandal_id, int dist_id, String mandal_name) {
	 this.mandal_id=mandal_id;
	 this.dist_id=dist_id;
	 this.mandal_name=mandal_name;
}
public Mandal() {
	// TODO Auto-generated constructor stub
}
public int getMandal_id() {
	return mandal_id;
}
public void setMandal_id(int mandal_id) {
	this.mandal_id = mandal_id;
}
public int getDist_id() {
	return dist_id;
}
public void setDist_id(int dist_id) {
	this.dist_id = dist_id;
}
public String getMandal_name() {
	return mandal_name;
}
public void setMandal_name(String mandal_name) {
	this.mandal_name = mandal_name;
}

}
