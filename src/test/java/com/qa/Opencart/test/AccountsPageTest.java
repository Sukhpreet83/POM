package com.qa.Opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Opencart.pages.AccountsPage;
import com.qa.Opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest {

	
	@BeforeClass
	public void accPageSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Description("This is to get the title of the account page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		System.out.println("acc page title is: " + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Description("This is to get the header of the account page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println("acc page header is: " + header);
		Assert.assertEquals(header, Constants.PAGE_HEADER);
	}

	@Description("This is to get the Account SEction List of the account page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void accSectionsListTest() {	
		
		List<String> actAccSecList = accPage.getAccountSectionsList();
		System.out.println("actual sections: " + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACC_SEC_LIST);
	}
	
	
	@Description("This is to check the logout button")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	

	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"Macbook Pro"}, 
			{"Macbook Air"}, 
			{"Apple"}
			};
	}
	
	@Test(priority = 5, dataProvider = "getSearchData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		String resultHeader = resultsPage.getSearchPageHeader();
		System.out.println("result header is : " + resultHeader);
		Assert.assertTrue(resultHeader.contains(productName));
	}
@DataProvider
public Object[][] getProductSelectData(){
return new Object[][] {
{"Macbook", "MacBook Air"},
{"Macbook", "MacBook Pro"},
{"Apple", "Apple Cinema 30\""}
};
}
	
////	@DataProvider
////	public Object[][] getProductSelectData(){
////		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
////	}
////	
	@Test(priority = 6, dataProvider = "getProductSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String header = productInfoPage.getProductHeaderText();
		System.out.println("product header : " + header);
		Assert.assertEquals(header, mainProductName);
	}
	

}
