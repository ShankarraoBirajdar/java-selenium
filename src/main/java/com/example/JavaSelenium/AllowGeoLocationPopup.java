package com.example.JavaSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AllowGeoLocationPopup {
  public static final String AUTOMATE_USERNAME = "YOUR_USERNAME";
  public static final String AUTOMATE_KEY = "YOUR_ACCESS_KEY";
  public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {

    // INIT CHROME OPTIONS
    EdgeOptions options = new EdgeOptions();
    Map < String, Object > prefs = new HashMap < String, Object > ();
    Map < String, Object > profile = new HashMap < String, Object > ();
    Map < String, Object > contentSettings = new HashMap < String, Object > ();
    
    options.addArguments("-inprivate");
    options.addArguments("--start-maximized");

    // SET CHROME OPTIONS
    // 0 - Default, 1 - Allow, 2 - Block
    contentSettings.put("geolocation", 1);
    profile.put("managed_default_content_settings", contentSettings);
    prefs.put("profile", profile);
    options.setExperimentalOption("prefs", prefs);

    WebDriver driver = new EdgeDriver(options) ;
    driver.get("https://the-internet.herokuapp.com/geolocation");
    driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
    Thread.sleep(5000);
    driver.quit();
  }
}

