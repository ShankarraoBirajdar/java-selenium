package com.example.JavaSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliTutorial {

	public static void main(String[] args) {

		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
//		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");
		
		SeleniumManager seleniumManager = SeleniumManager.getInstance();
//		String browserPath = seleniumManager.getInstance().getDriverPath(chromeOptions, false).getBrowserPath();
//		String driverPath = seleniumManager.getInstance().getDriverPath(chromeOptions, false).getDriverPath();
//		System.out.println("Browser Location:: " + browserPath);
//		System.out.println("Driver Location:: " + driverPath);
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		// Initialize ChromeDriver

		WebDriver driver = new ChromeDriver(chromeOptions);

		// Open the website

		driver.get("https://www.irctc.co.in/nget/train-search");

		// Initialize Sikuli Screen

		Screen screen = new Screen();

		// Define the image patterns

		Pattern imagePattern = new Pattern("C:\\1707393671171.png");

		try {

			// Click on the image using SikuliX

			screen.type(imagePattern, "arunch1485");

			System.out.println("Clicked on the image successfully.");

		} catch (Exception e) {

			System.out.println("Failed to click on the image: " + e.getMessage());

		}

		// Close the browser

		driver.quit();

	}

}
