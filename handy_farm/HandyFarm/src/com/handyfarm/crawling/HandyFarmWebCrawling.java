/**
    * @author 임예나(Thread), 김연주(Buffer)
    * email : yenaim0108@gmail.com, sym61503@naver.com
*/


package com.handyfarm.crawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class HandyFarmWebCrawling {
		Process process;
		long timeInterval = 3600000; // 1시간
		String path = this.getClass().getResource("").getPath();
		
		// 1시간 마다 웹크로링으로 정보 가져오기
		public Thread webCrawling = new Thread(() -> {
			path = path.substring(1, path.indexOf(".metadata"));
			path = path.replace("/", "\\");
			path += "HandyFarm\\src\\com\\handyfarm\\crawling\\webCrawling_db_connect_v1.1.py";
			while (true) {
				try {
					process = Runtime.getRuntime().exec("python " + path);
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
					
				}catch(IOException | InterruptedException e) {
					e.printStackTrace();
				}
				
				// timeInterval 만큼 sleep
				try {
					Thread.sleep(timeInterval);
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
}