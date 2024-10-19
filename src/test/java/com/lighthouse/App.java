package com.lighthouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

//	https://googlechrome.github.io/lighthouse/viewer/?psiurl=https://www.theaa.com
	public static void main(String[] args) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "chrome-debug --port=9222","https://www.google.com/");
		builder.redirectErrorStream(true);
	    Process p = builder.start();

		System.setProperty("webdriver.http.factory", "jdk-http-client");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
		
		ChromeDriver driver = new ChromeDriver(options);
		



		String URL = driver.getCurrentUrl();
         driver.manage().window().maximize();
         try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//         ("cmd.exe", "/c", "lighthouse", URL, "--port=9222","--preset=desktop","--view" ,"--output=html", "--output-path=lighthouse_report.html"); for desktop
//         ("cmd.exe", "/c", "lighthouse", URL, "--port=9222","--screenEmulation.mobile", "--screenEmulation.width=360", "--screenEmulation.height=640", "--screenEmulation.dev","--view" ,"--output=html", "--output-path=lighthouse_mobile_report.html"); for mobile
		ProcessBuilder builder2 = new ProcessBuilder("cmd.exe", "/c", "lighthouse", URL, "--port=9222","--screenEmulation.mobile", "--screenEmulation.width=360", "--screenEmulation.height=640", "--screenEmulation.dev","--view" ,"--output=html", "--output-path=lighthouse_mobile_report.html");
		builder2.redirectErrorStream(true);
		Process p2 = builder2.start();
		

		BufferedReader r = new BufferedReader(new InputStreamReader(p2.getInputStream()));
		String line;
		while (true) {
		   line = r.readLine();
		     if (line == null) {
		     break;
		   }
		  System.out.println(line);
		}
		
		r.close();
		driver.close();
         
	}

}
