package com.example.JavaSelenium;

 class Parent {

	private int num=0;
	public Parent(int num) {
		this.num=num;
	}
	
	public int getNum() {
		return this.num;
	}
}

 public class Child extends Parent{
	private int val=0;
	public Child(int val,int num) {
		super(num);
		this.val=val;
	}
	
	public int getVal() {
		return this.val;
	}
	public static void main(String[] args) {
		Child amit = new Child(1,2);
		System.out.println("Parent: "+amit.getNum());
		System.out.println("Child: "+amit.getVal());

	}
}
