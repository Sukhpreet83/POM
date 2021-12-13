package com.qa.Opencart.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.Opencart.factory.DriverFactory;


public class TestAllureListener extends DriverFactory implements ITestListener
{

	
	private static String getTestMethodName(ITestResult iTestResult) {
		System.out.println(iTestResult.getMethod().getMethodName());
		System.out.println(iTestResult.getMethod().getConstructorOrMethod().getName());
		return iTestResult.getMethod().getConstructorOrMethod().getName();
		
	}

	
//	// HTML attachments for Allure
//	@Attachment(value = "{0}", type = "text/html")
//	public static String attachHtml(String html) {
//		return html;
//	}
	
	
	// Text attachments for Allure
	@Attachment(value = "Page ScreenShot",type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) 
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);	
	}
	

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
			System.out.println("I am in onTestFailure method " + getTestMethodName(result) + " failed");
			if (getDriver() instanceof WebDriver) {
				System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
				saveScreenshotPNG(getDriver());
			}
			// Save a log on allure.
			saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");		
		}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(result) + " succeed");
		
	}

	

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(result) + " skipped");
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));
	}


	@Override
	public void onStart(ITestContext context) {
		System.out.println("I am in onStart method " + context.getName());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("I am in onFinish method " + context.getName());
		
	}
	
	
}



