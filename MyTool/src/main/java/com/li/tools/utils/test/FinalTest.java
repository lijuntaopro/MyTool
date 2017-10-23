package com.li.tools.utils.test;

public class FinalTest {
	public static void main(String[] args) {
		A a = new A();
		a.name = "A1";
		transform(a);
		System.out.println(a.name);
	}
	
	public static void transform(A a){
		A a2 = new A();
		a2.name = "A2";
		a = a2;
	}
}

class A {
	public String name;
}