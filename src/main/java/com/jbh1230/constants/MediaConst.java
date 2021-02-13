package com.jbh1230.constants;

import java.util.*;
import java.util.List;

public enum MediaConst {
	//IMG_JPG("jpg", "img"), IMG_PNG("png", "img"), IMG_JPEG("jpeg", "img"), VD_MOV("mov", "vd"), VD_MP4("mp4", "vd"), VD_AVI("avi", "vd"), 
	MEDIA_TYPE_IMG("IMG", Arrays.asList("jpg", "png", "jpeg", "gif")),
	MEDIA_TYPE_VD ("VD" , Arrays.asList("mov", "mp4", "avi")),
	EMPTY("EMPTY", Collections.EMPTY_LIST);

	final private String name;
	final private List<String> list;

	MediaConst(String name, List<String> list) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.list = list;
	}

	public static MediaConst findType(String val) {
		return Arrays.stream(MediaConst.values()).filter(type -> type.hasType(val)).findAny().orElse(EMPTY);
	}
	
	public boolean hasType(String type) {
		return list.stream().anyMatch(any -> any.equals(type));
	}
	
}
