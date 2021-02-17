package com.jbh1230.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum FileTypeConst {
	/*
	 * Enum 구성
	 * {코드, [항목...]}
	 */
	AUDIO("AUDIO", Arrays.asList("mp3", "wav", "wma", "ogg", "au", "rm", "mid", "aac", "flac", "gsm", "dct", "m4a", "raw", "amr")),
	VIDEO("VIDEO", Arrays.asList("avi", "mp4", "mkw", "wmv", "mov", "ts", "tp", "flv", "3gp", "m4v")),
	IMAGE("IMAGE", Arrays.asList("jpg", "jpeg", "png", "gif", "tif", "tiff", "rle", "dib", "heic")),
	EMPTY("EMPTY", Collections.EMPTY_LIST);
	
	private String type;
	private List<String> extensions;

	FileTypeConst(String type, List<String> extensions) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.extensions = extensions;
		
	}
	
	public boolean isType(String type) {
		return this.type.equals(type);
	}
	
	public boolean hasType(String type) {
		return extensions.stream().anyMatch(any -> any.equals(type));
	}
	
	public static FileTypeConst findTypeByText(String code) {
		return Arrays.stream(FileTypeConst.values()).filter(type -> type.isType(code)).findAny().orElse(EMPTY);
	}
	
	public static FileTypeConst findType(String extension) {
		return Arrays.stream(FileTypeConst.values()).filter(type -> type.hasType(extension)).findAny().orElse(EMPTY);
	}
	
}
