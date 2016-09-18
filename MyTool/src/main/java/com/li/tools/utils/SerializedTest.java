package com.li.tools.utils;

import java.io.Serializable;

public class SerializedTest extends SerializedTestParent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hhah;
	static private String hhah2;
	final private String hhah3 = "";
	private transient String hhah4;
	
}

class SerializedTestParent{
	public String hh;
}