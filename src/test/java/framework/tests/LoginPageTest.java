package framework.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.pageObject.LoginPage;
import resources.Base;

public class LoginPageTest extends Base{
	WebDriver driver;
	LoginPage lp;
	@BeforeTest
	public void setUp() throws IOException {
		driver = initialiseDriver();
	}
	
	@BeforeMethod
	public void setData() {
		driver.get(prop.getProperty("url"));
		log.info("Landed on login page");
		lp = new LoginPage(driver);
	}
	@Test(dataProvider = "getData")
	public void checkLoginPage(String username) {
		String password = prop.getProperty("password");
		lp.getEmail().sendKeys(username);
		log.debug("Passed "+username+" as username");
		lp.getPassword().sendKeys(password);
		log.debug("Passed "+password+" as password");
		lp.loginButton().click();
		if(username.equals("locked_out_user")) {
			log.error("Logged in with wrong username: "+username);
			Assert.assertEquals(lp.errorMsg(), prop.getProperty("errormsg"));
		}
		log.debug("Landed on home page");
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
		log.info("Closed browser");
	}
	
	@DataProvider
	public Object[] getData() {
		Object[] data = new Object[4];
		data[0]="standard_user";
		data[1]="locked_out_user";
		data[2]="problem_user";
		data[3]="performance_glitch_user";
		
		return data;
	}

}
