package com.jbh1230.http;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class SimpleURL {

	private URL url;
	private HttpURLConnection conn;
	private StringBuilder sb;
	
	public SimpleURL() {
		this.sb = new StringBuilder();
	}
	
	public SimpleURL(String strURL) throws IOException {
		if(strURL.indexOf("https://") > -1 && strURL.lastIndexOf(":") > -1) {
			this.url = new URL("https", strURL.substring(8, strURL.lastIndexOf(":")) , Integer.parseInt(strURL.substring(strURL.lastIndexOf(":") + 1)), strURL, new sun.net.www.protocol.https.Handler());
		}
		else if(strURL.indexOf("http://") > -1 && strURL.lastIndexOf(":") > -1) {
			this.url = new URL("http", strURL.substring(7, strURL.lastIndexOf(":")) , Integer.parseInt(strURL.substring(strURL.lastIndexOf(":") + 1)), strURL, new sun.net.www.protocol.http.Handler());
		}
		else {
			this.url = new URL(strURL);
		}
		//this.url = new URL(strURL);
		this.conn = (HttpURLConnection) url.openConnection();
		this.conn.setDoInput(true);
		this.conn.setDoOutput(true);
		this.sb = new StringBuilder();
	}
	
	public SimpleURL(URL url) throws IOException {
		this.conn = (HttpURLConnection) url.openConnection();
		this.conn.setDoInput(true);
		this.conn.setDoOutput(true);
		this.sb = new StringBuilder();
	}
	
	public void setURL(URL url) throws IOException {
		this.conn = (HttpURLConnection) url.openConnection();
		this.conn.setDoInput(true);
		this.conn.setDoOutput(true);
	}
	
	public void setMethod(String method) throws ProtocolException {
		switch(method) {
		case "GET": case "POST": case "PUT": case "DELETE": case "PATCH":
			conn.setRequestMethod(method);
			break;
		default:
			throw new ProtocolException("메서드가 존재하지 않음.");
		}
	}
	
	public void setParameter(String key, String val) {
		if(!sb.toString().isEmpty()) {
			sb.append("&");
		}
		sb.append(key);
		sb.append("=");
		sb.append(val);
	}
	
	
	public String connect() throws IOException {
		String result = "";
		
		if(!sb.toString().isEmpty()) {
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(sb.toString());
			
			writer.flush();
			writer.close();
			os.close();
		}
		
		conn.connect();
		
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
		 // 응답 내용(BODY) 구하기        
	        try (InputStream in = conn.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	            
	            byte[] buf = new byte[1024 * 8];
	            int length = 0;
	            while ((length = in.read(buf)) != -1) {
	                out.write(buf, 0, length);
	                sb.append(buf);
	            }
	            result = new String(out.toByteArray(), "UTF-8");
	            System.out.println(sb.toString());
	        }
		}
		else {
			System.out.println(conn.getResponseCode());
			result = Integer.toString(conn.getResponseCode());
		}
        
        // 접속 해제
        conn.disconnect();
		
		return result;
	}
	
	
}
