package com.qa.Opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.Opencart.pages.AccountsPage;
import com.qa.Opencart.pages.LoginPage;
import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {
	
	@Description("This is Login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, Errors.TITLE_ERROR_MESSAGE);
	}

	@Description("This is Login Page Header")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String header = loginPage.getPageHeaderText();
		System.out.println("Login Page header is :" + header);
		Assert.assertEquals(header, Constants.PAGE_HEADER, Errors.HEADER_ERROR_MESSG);
	}
	
	@Description("This is Forgot password link")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("This is login Feature")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
//		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		AccountsPage accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());

		//or below code
//		AccountsPage accPage = new AccountsPage(driver);
//		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
