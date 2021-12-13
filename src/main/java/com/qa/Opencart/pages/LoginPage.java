package com.qa.Opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//private By locators	
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By header = By.cssSelector("div#logo h1 a");
	private By registerLink = By.linkText("Register");

	// constructor
	public LoginPage(WebDriver driver)
	{
		this.driver = driver; 
		elementUtil = new ElementUtil(driver);
	}

	@Step("This is to get the title of the page")
	public String getLoginPageTitle()
	{
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	@Step("This is to get the Header of the page")
	public String getPageHeaderText()
	{
		return elementUtil.doGetText(header);
		
	}
	
	@Step("This is to check the forgot link password")
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("This is to login into the application")
	public AccountsPage doLogin(String un,String pwd)
	{
		
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password,pwd );
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		
	}
	
	
	public RegistrationsPage nagigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationsPage(driver);
	}
	
}

