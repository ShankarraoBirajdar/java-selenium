package com.example.javaselenium;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class App {
	protected WebDriver driver = null;
	@BeforeTest
	public void startUp() {
		WebDriverFactory driverFactory = new WebDriverFactory();
		
		driver = driverFactory.getDriver("chrome");
	}
	
	@Test
	public void test() {
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		System.out.println("Title:: "+ title);
		Assert.assertEquals(title, "Google");
	}
	
	@AfterTest
	public void cleanUp() {
		driver.quit();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
