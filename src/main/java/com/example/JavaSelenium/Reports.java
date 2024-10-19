
package com.example.JavaSelenium;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	private static final String CODE1 = "{\n    \"theme\": \"standard\",\n    \"encoding\": \"utf-8\n}";
	private static final String CODE2 = "{\n    \"protocol\": \"HTTPS\",\n    \"timelineEnabled\": false\n}";

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
		extent.attachReporter(spark);

		extent.createTest("ScreenCapture").addScreenCaptureFromPath("extent.png")
				.pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(chromeOptions);

		String base64Code = captureScreenshots(driver);
		String path = captureScreenshots(driver, "Google.jpg");
		extent.createTest("Screenshot 1", "This is for attaching the screenshots").info("this is ingo msg")
				.addScreenCaptureFromBase64String(base64Code, "base64Code").addScreenCaptureFromPath(path, "path");

		ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), "Validate the UKB Journey");
		ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Validate the title of cover page");

		scenario.createNode(new GherkinKeyword("Given"), "Launch the browser").info("Launch the browser");

		driver.get("https://www.theaa.com/breakdown-cover/sales/cover");
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		scenario.createNode(new GherkinKeyword("Given"), "I navigate to Cover page")
				.addScreenCaptureFromPath(captureScreenshots(driver, "img1.jpg"), "I navigate to Cover page").pass("pass");

		String title = driver.getTitle();
		scenario.createNode(new GherkinKeyword("And"), "I validate the title")
				.addScreenCaptureFromPath(captureScreenshots(driver, "img2.jpg"), "Title is " + title).pass("pass");

		WebElement element=driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
		scrollIntoView(driver,element);
		focus(driver,element);
		try {
			Thread.sleep(7000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		element.click();
		scenario.createNode(new GherkinKeyword("When"), "I click the continue Button")
				.addScreenCaptureFromPath(captureScreenshots(driver, "img3.jpg"), "Title is " + title).pass("pass");

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		WebElement element2 = driver.findElement(By.xpath("(//span[@class='price'])[1]"));
		scrollIntoView(driver,element2);
		focus(driver,element2);
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String price = element2.getText();
		scenario.createNode(new GherkinKeyword("Then"), "I print the price")
				.addScreenCaptureFromPath(captureScreenshots(driver, "img4.jpg"), "price is " + price).pass("pass");

		driver.quit();
		scenario.createNode(new GherkinKeyword("And"), "close the browser").info("close the browser");

		extent.createTest("LogLevels").info("info").pass("pass").warning("warn").skip("skip").fail("fail");

		extent.createTest("Text").info("info").info("<b>info</b>").info("<i>info</i>");

		extent.createTest("CodeBlock").generateLog(Status.PASS, MarkupHelper.createCodeBlock(CODE1, CODE2));

		extent.createTest("ParentWithChild").createNode("Child")
				.pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");

		extent.createTest("Tags").assignCategory("MyTag")
				.pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");

		extent.createTest("Authors").assignAuthor("TheAuthor")
				.pass("This test 'Authors' was assigned by a special kind of author tag.");

		extent.createTest("Devices").assignDevice("TheDevice")
				.pass("This test 'Devices' was assigned by a special kind of devices tag.");

		extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
				.fail(new RuntimeException("A runtime exception occurred!"));

		extent.flush();

		Desktop.getDesktop().browse(new File("target/Spark/Spark.html").toURI());
	}

	public static String captureScreenshots(WebDriver driver, String fileName) {
		/* File */
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./target/Spark/" + fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FILE Screenshot saved successfully ");
		return destFile.getAbsolutePath();
	}

	public static String captureScreenshots(WebDriver driver) {
		/* Base64 */
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("BASE64 Screenshot saved successfully ");
		return base64code;
	}
	public static void scrollIntoView(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void focus(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');", element);

	}
}
