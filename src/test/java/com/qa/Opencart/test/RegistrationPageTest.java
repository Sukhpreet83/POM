package com.qa.Opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.nagigateToRegisterPage();
	}

	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "testautomation"+random.nextInt(500000)+"@gmail.com";
		System.out.println(email);
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "getRegTestData")
	public void registrationTest(String firstName, String lastName, String telephone, String password,String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName,getRandomEmail(), telephone, password, subscribe));
	}
	
}
