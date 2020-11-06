package com.handyfarm.crawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class webCrawlingRun {
	public static void main(String[] args) {
		
		Process process;   
		try {
			File file = new File("src\\com\\handyfarm\\crawling\\webCrawling_db_connect_v1.1.py");
	        String rootPath = file.getAbsolutePath();
	        process = Runtime.getRuntime().exec("python " + rootPath);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
			String data;
			while((data = inReader.readLine()) != null) {
				System.out.println(data);
			}
			
			while((data = errReader.readLine()) != null) {
				System.out.println(data);
			}
			process.waitFor();
			
		}catch(IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
