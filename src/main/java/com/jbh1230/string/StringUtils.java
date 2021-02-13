package com.jbh1230.string;

import com.jbh1230.constants.StringConst;

public class StringUtils {

	public static String lpad(int num, int size) {
		return lpad(num, size, "0");
	}
	
	public static String rpad(int num, int size) {
		return rpad(num, size, "0");
	}
	
	public static String lpad(int num, int size, String fill) {
		return pad(num, size, fill, StringConst.LPAD);
	}
	
	public static String rpad(int num, int size, String fill) {
		return pad(num, size, fill, StringConst.RPAD);
	}
	
	private static String pad(int num, int size, String fill, StringConst sc) {
		String defFill = "0";
		if(!fill.isEmpty() && !fill.equals("") && fill != null && fill.length() == 1) {
			defFill = fill;
		}
		String sNum = Integer.toString(num);
		int baseLength = sNum.length();
		StringBuilder sb = new StringBuilder();
		if(baseLength > size) {
			return ""; 
		}
		else {
			if(sc == StringConst.RPAD)
				sb.append(sNum);
			for(int i = 0; i < size - baseLength; i++) {
				sb.append(defFill);
			}
			if(sc == StringConst.LPAD)
				sb.append(sNum);
		}
		return sb.toString();
	}
}
