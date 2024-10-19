package com.example.JavaSelenium;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

	public static void main(String[] args) {
		String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ROOT);
		System.out.println("OS Name: " + os);
		
//		storeSession();
//		chromInDebuggingMode();
//		chromeForTesting();
//		selenium4();
		webDriverManager();
	}

	@Test
	public void Test() {
		String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ROOT);
		System.out.println("OS Name: " + os);
		selenium4();
		webDriverManager();
	}
	
	public static void storeSession() {
		// add chrome options
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
//		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");
		//chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--user-data-dir=C:\\ChromeDebug");
//        String chatLink = "https://web.whatsapp.com/send/?phone=" + "+91123456789" + "&text=" + "Hi";
		String whatsappLink = "https://web.whatsapp.com/";

		// create a driver
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get(whatsappLink);
		
		
		System.out.println("ChromeDriver launched successfully...");
//		driver.quit();
//		System.out.println("ChromeDriver closed successfully...");
	}
	
	

	
	public static void selenium4() {
		// add chrome options
		System.out.println("Execution started");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
//		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");
//		chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--no-sandbox");
		
		chromeOptions.addArguments("user-agent=automation");
		
		
		// create a driver
		WebDriver driver = new ChromeDriver(chromeOptions);
		
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toLowerCase();
	    System.out.println(browserName);
	    String os = cap.getPlatformName().toString();
	    System.out.println(os);
	    String v = cap.getBrowserVersion().toString();
	    System.out.println(v);
		String url = "https://www.google.com/";
		
		driver.get(url);
		System.out.println("ChromeDriver launched successfully...");
		System.out.println("Page Title:: "+driver.getTitle());
		driver.quit();
		System.out.println("ChromeDriver closed successfully...");
	}

	public static void webDriverManager() {
		System.out.println("Execution started");
		// add chrome options
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");
		System.out.println("getDownloadedDriverPath: " + WebDriverManager.getInstance().getDownloadedDriverPath());
		System.out
				.println("getDownloadedDriverVersion: " + WebDriverManager.getInstance().getDownloadedDriverVersion());
		System.out.println("getBrowserPath: " + WebDriverManager.getInstance().getBrowserPath());

		// create a driver
		WebDriver driver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
		driver.get("https://web.whatsapp.com/");
		System.out.println("ChromeDriver launched successfully...");
		driver.quit();
		System.out.println("ChromeDriver closed successfully...");
	}

}
