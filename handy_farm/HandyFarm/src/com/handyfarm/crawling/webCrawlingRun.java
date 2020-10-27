package com.handyfarm.crawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class webCrawlingRun {
	public static void main(String[] args) {
		
		Process process;
		try {
			process = Runtime.getRuntime().exec("cmd.exe /c C:\\Users\\yenai\\AppData\\Local\\Programs\\Python\\Python38-32\\python.exe webCrawling.py");
			BufferedReader inReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			
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
