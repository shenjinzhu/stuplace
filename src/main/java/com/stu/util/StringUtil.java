package com.stu.util;

public class StringUtil {

	public static int changeInt(String str) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return 0;
		}
	}
}
