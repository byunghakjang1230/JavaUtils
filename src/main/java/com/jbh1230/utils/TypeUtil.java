package com.jbh1230.utils;

import java.math.BigDecimal;

public class TypeUtil {
	
	public static String toString(int num) {
		return Integer.toString(num);
	}

	public static String toString(long num) {
		return Long.toString(num);
	}
	
	public static String toString(BigDecimal num) {
		return num + "";
	}
	
}
