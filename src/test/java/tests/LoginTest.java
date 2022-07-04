package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountsPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;
	Logger log;

	@BeforeMethod
	public void setup() throws IOException {
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException {

		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingpage.loginOption().click();
		log.debug("Clicked on login option");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginBtn().click();
		log.debug("Clicked on Login Button");

		AccountsPage accountspage = new AccountsPage(driver);
		String actualResult = null;
		try {

			if (accountspage.editaccountinformationlink().isDisplayed()) {
				log.debug("User got logged in");
				actualResult = "Successfull";
			}
		} catch (Exception e) {
			log.debug("User didn't log in");
			actualResult = "Failure";
		}

		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login Test got passed");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		log.debug("Browser got closed");
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "mahe.sorab@gmail.com", "Gold@123", "Successfull" },
				{ "dummy@gmail.com", "dummy@123", "Failure" } };
		return data;
	}
}
