package com.jbh1230.utils;

import com.jbh1230.constants.StringConst;

public class StringUtil {

	public static String lpad(int num, int size) {
		return lpad(num, size, "0");
	}
	
	public static String rpad(int num, int size) {
		return rpad(num, size, "0");
	}
	
	public static String lpad(String val, int size) {
		return lpad(val, size, "0");
	}
	
	public static String rpad(String val, int size) {
		return rpad(val, size, "0");
	}
	
	public static String lpad(int num, int size, String fillChar) {
		return pad(TypeUtil.toString(num), size, fillChar, StringConst.LPAD);
	}
	
	public static String rpad(int num, int size, String fillChar) {
		return pad(TypeUtil.toString(num), size, fillChar, StringConst.RPAD);
	}
	
	public static String lpad(String val, int size, String fillChar) {
		return pad(val, size, fillChar, StringConst.LPAD);
	}
	
	public static String rpad(String val, int size, String fillChar) {
		return pad(val, size, fillChar, StringConst.RPAD);
	}
	
	private static String pad(String num, int size, String fillChar, StringConst sc) {
		String defFill = "0";
		if(!fillChar.isEmpty() && !fillChar.equals("") && fillChar != null && fillChar.length() == 1) {
			defFill = fillChar;
		}
		int baseLength = num.length();
		StringBuilder sb = new StringBuilder();
		if(baseLength > size) {
			return ""; 
		}
		else {
			if(sc == StringConst.RPAD)
				sb.append(num);
			for(int i = 0; i < size - baseLength; i++) {
				sb.append(defFill);
			}
			if(sc == StringConst.LPAD)
				sb.append(num);
		}
		return sb.toString();
	}
}
