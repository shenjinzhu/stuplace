package com.stu.util;

public class Tools {

	public static void sleep(int num) {
		try {
			Thread.sleep(num*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
