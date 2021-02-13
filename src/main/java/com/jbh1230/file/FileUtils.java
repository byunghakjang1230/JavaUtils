package com.jbh1230.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class FileUtils {

	/*
	 * 생성시간 리턴. 속성값 읽어오기 실패 시 최근 수정시간 가져오기.
	 */
	public static Date getCreateTime(File file) {
		try {
			return new Date(Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime().toMillis());
		} catch (IOException e) {
			return getLastModifyTime(file);
		}
	}
	
	public static Date getLastModifyTime(File file) {
		return new Date(file.lastModified());
	}
	
	public static String getFullName(File file) {
		return file.getName();
	}
	
	public static String getNameWithoutExtention(File file) {
		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}
	
	public static String getFulPath(File file) {
		return file.getPath();
	}
	
	public static String getFolderPath(File file) {
		return file.getParent();
	}
	
	public static String getExtension(File file) {
		return file.getName().substring(file.getName().lastIndexOf(".") + 1);
	}
	
	public static File copyFile(String fromFilePath, String toFilePath) throws FileNotFoundException {
		return copyFile(new File(fromFilePath), new File(toFilePath));
	}
	
	public static File copyFile(File fromFile, File toFile) throws FileNotFoundException {
		byte[] buffer = new byte[1024];
		if(!fromFile.exists())
			throw new FileNotFoundException("복사대상 파일이 존재하지 않습니다.");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile));
		try {
			int fbyte = 0;
			while((fbyte = bis.read(buffer, 0, 1024)) != -1){
				bos.write(buffer, 0, fbyte);
				bos.flush();
			}
			System.out.println("File Write Completed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! Exception Log !!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("File Write Error");
			e.printStackTrace();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		} finally {
			try {
				bis.close();
				bos.close();
				System.out.println("Buffer Close Completed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! Exception Log !!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println("Stream Close Error");
				e.printStackTrace();
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		}
		return new File(toFile.getPath());
	}
}
