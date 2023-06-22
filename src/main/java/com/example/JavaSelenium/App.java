package com.example.JavaSelenium;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

	
	public static void main(String[] args) {
		// driver path
//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ROOT);
		System.out.println("OS Name: " + os);
		selenium4();
		webDriverManager();
	}
	
	@Test
	public void Test() {
		String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ROOT);
		System.out.println("OS Name: " + os);
		selenium4();
		webDriverManager();
	}

	public static void selenium4() {
		// add chrome options
		System.out.println("Execution started");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");

		SeleniumManager seleniumManager = SeleniumManager.getInstance();
		String pathString = seleniumManager.getDriverPath(chromeOptions);
		System.out.println("getDriverPath: " + pathString);

		// create a driver
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get("https://google.com");
		System.out.println("ChromeDriver launched successfully...");
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
		driver.get("https://google.com");
		System.out.println("ChromeDriver launched successfully...");
		driver.quit();
		System.out.println("ChromeDriver closed successfully...");
	}

}
