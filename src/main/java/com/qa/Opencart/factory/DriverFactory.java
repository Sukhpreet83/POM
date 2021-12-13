package com.qa.Opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static String highlight;
	WebDriver driver;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	

	public WebDriver initDriver(Properties prop)
	{
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());		
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}	
		else if(browserName.equalsIgnoreCase("Safari"))
		{
		//	driver = new SafariDriver();		
			tlDriver.set(new SafariDriver());
		}
			
		else
			System.out.println("Please pass correct Browser Name");
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		
		return getDriver();
	}
	
	public WebDriver getDriver()
	{
		return tlDriver.get(); 
	}
	
	
	public Properties initProperties()
	{
		
		String env = System.getProperty("env");
		Properties prop = null;
		FileInputStream ip = null;
		
		try
		{
		if(env == null)
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		else if(env.equalsIgnoreCase("qa"))
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		else if(env.equalsIgnoreCase("uat"))
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
		
		prop = new Properties();
		prop.load(ip);
		
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	public String getScreenshot()
	{
		File scr = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir")+"/screenshot/" + System.currentTimeMillis()+".png";
		File Dest = new File(Path);
		try {
			FileUtils.copyFile(scr, Dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Path;
		
	}
}
