package com.example.JavaSelenium;



import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.devtools.v125.network.model.Request;
import org.openqa.selenium.devtools.v125.network.model.RequestId;
import org.openqa.selenium.devtools.v125.network.model.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DevtoolsExamples {

	WebDriver driver = null;

	@BeforeTest
	public void startUp() {
		/* chromeOptions */
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
//		chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--start-maximized");

		/* create a driver */
		driver = new ChromeDriver(chromeOptions);
		System.out.println("ChromeDriver launched successfully...");
		
	}

	@Test
	public void test() {

	
		DevTools devTools = ((ChromeDriver) driver).getDevTools(); // Create devTool instance
		devTools.createSession();

		devTools.send(Network.clearBrowserCache());
        devTools.send(Network.setCacheDisabled(true));
        
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), requestConsumer -> {
            Request request = requestConsumer.getRequest();
//            System.out.println(request.getUrl());

        });
        
        final RequestId[] requestId = new RequestId[1];
        
        devTools.addListener(Network.responseReceived(), responseConsumer -> {
            Response response = responseConsumer.getResponse();
            requestId[0] = responseConsumer.getRequestId();
//            .equals("https://api.digital.theaa.com/breakdown/sales/v1/offer")
            if (response.getUrl().contains("https://api.digital.theaa.com")) {
            	String responseBody = devTools.send(Network.getResponseBody(requestId[0])).getBody();
            	 System.out.println(response.getStatus() + " " + response.getUrl()+"\n"+responseBody);
            }
           
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


//        final RequestId[] requestIds = new RequestId[1];
//        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.of(100000000)));
//        devTools.addListener(Network.responseReceived(), responseReceived -> {
//            
//
//            requestIds[0] = responseReceived.getRequestId();
//            String url = responseReceived.getResponse().getUrl();
//
//            int status = responseReceived.getResponse().getStatus();
//            String type = responseReceived.getType().toJson();
//            String headers = responseReceived.getResponse().getHeaders().toString();
//
//            String  responseBody = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
//            if (url.equals("https://api.digital.theaa.com/breakdown/sales/v1/offer")) {
//            	System.out.println(status+" "+url+" "+type+" "+headers+"\n "+responseBody);
//			}
////            https://api.digital.theaa.com/breakdown/sales/v1/offer XHR 
//		
//        });

        driver.get("https://www.theaa.com/breakdown-cover/sales/cover");
	}

	@AfterTest
	public void showtDown() {
		/* close the driver */
		driver.quit();
		System.out.println("ChromeDriver closed successfully...");
	}

}
