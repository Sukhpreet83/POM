package com.qa.Opencart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.Opencart.factory.DriverFactory;
import com.qa.Opencart.pages.AccountsPage;
import com.qa.Opencart.pages.LoginPage;
import com.qa.Opencart.pages.ProductInfoPage;
import com.qa.Opencart.pages.RegistrationsPage;
import com.qa.Opencart.pages.ResultsPage;

public class BaseTest {

	WebDriver driver;
	Properties prop;
	DriverFactory df;
	LoginPage loginPage;
	AccountsPage accPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	RegistrationsPage regPage;
	
	
	
	
	@BeforeTest
	public void Setup()
	{
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop);
		
		loginPage = new LoginPage(driver);
	}	
	
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}