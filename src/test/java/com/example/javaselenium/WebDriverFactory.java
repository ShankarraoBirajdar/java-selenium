package com.example.javaselenium;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	Map<String, Supplier<WebDriver>> driverMap =  new HashMap<String, Supplier<WebDriver>>();
	
	public WebDriverFactory() {
		driverMap.put("chrome", this::createChromeDriver);
		driverMap.put("firefox", this::createFireFoxDriver);
		driverMap.put("edge", this::createEdgeDriver);
	}
	
	public WebDriver getDriver(String browserName) {
		return driverMap.getOrDefault(browserName.toLowerCase().trim(), ()->{
			System.out.println("Browser Not Found....");
			throw new RuntimeException("Browser Not Found....");
		}).get();
	}
	
	
	private WebDriver createChromeDriver() {
		return new ChromeDriver();
	}
	
	private WebDriver createFireFoxDriver() {
		return new FirefoxDriver();
	}
	
	private WebDriver createEdgeDriver() {
		return new EdgeDriver();
	}
}
