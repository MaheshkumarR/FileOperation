package com.infrrd.file.Model;

public class FileSrc {
private String filedestination;
private int clonecount = 1;
public int getClonecount() {
	return clonecount;
}

public void setClonecount(int clonecount) {
	this.clonecount = clonecount;
}

private static FileSrc singleInstance=null;
public static FileSrc getInstance() 
{ 
    if (singleInstance == null) 
    	singleInstance = new FileSrc(); 

    return singleInstance; 
}

public String getFiledestination() {
	return filedestination;
}

public void setFiledestination(String filedestination) {
	this.filedestination = filedestination;
}

}
