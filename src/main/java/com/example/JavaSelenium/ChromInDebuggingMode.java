package com.example.JavaSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromInDebuggingMode {
	private static WebDriver driver;

	public static void main(String[] args) {
		chromInDebuggingMode();
	}

	public static void chromInDebuggingMode() {
		/*
		 * set envirnment variable - chrome location chrome.exe
		 * -remote-debugging-port=65252 --user-data-dir="C:\ChromeDebug"
		 */
		System.setProperty("webdriver.http.factory", "jdk-http-client");

		/* add chrome options */
		ChromeOptions chromeOptions = new ChromeOptions();
		
		/*
		 * pass the debuggerAddress and pass the port along with host. Since I am
		 * running test on local so using localhost
		 */
		chromeOptions.setExperimentalOption("debuggerAddress", "localhost:65253");
		
		/* create a driver */
		driver = new ChromeDriver(chromeOptions);

		sendKeys("//input[@id='firstName0']", "Shankar");
		sendKeys("//input[@id='lastName0']", "Birajdar");
		sendKeys("//input[@type='email']", "abc@test.com");

		/* close the driver */
		driver.quit();
		System.out.println("ChromeDriver closed successfully...");
	}

	public static void sendKeys(String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
//		element.click();
		element.clear();
		element.sendKeys(value);
	}

}
