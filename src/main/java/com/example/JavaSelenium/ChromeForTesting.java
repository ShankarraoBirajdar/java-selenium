package com.example.JavaSelenium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.manager.SeleniumManagerOutput.Result;

public class ChromeForTesting {

	public static void main(String[] args) {
		chromeForTestingWithSelenium11();
	}

	public static void chromeForTestingWithSelenium11() {

		/*
		 * Chrome for Testing has been created purely for browser automation and testing
		 * purposes, and is not suitable for daily browsing.
		 */

//		Map<String, String> mobileEmulation = new HashMap<>();
//
//		mobileEmulation.put("deviceName", "iPad Pro");


		
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
//		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
//		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//		chromeOptions.addArguments("--disable-infobars");
//		chromeOptions.addArguments("--disable-notifications");
//		chromeOptions.setBrowserVersion("115");
//		chromeOptions.setBinary("C:\\Users\\shabiraj\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
		
		SeleniumManager seleniumManager = SeleniumManager.getInstance();
		Result result = seleniumManager.getBinaryPaths(Arrays.asList("--driver-version","--browser-path","--browser-version"));
		System.out.println(result.getBrowserPath());
//		String browserPath = seleniumManager.getInstance().getDriverPath(chromeOptions, false).getBrowserPath();
//		String driverPath = seleniumManager.getInstance().getDriverPath(chromeOptions, false).getDriverPath();
//		System.out.println("Browser Location:: " + browserPath);
//		System.out.println("Driver Location:: " + driverPath);
		
		/* create a driver */
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.google.com");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ChromeDriver launched successfully...");
		
		/* close the driver */
		driver.quit();
//		System.out.println("ChromeDriver closed successfully...");
	}

}
