package framework.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import framework.pageObject.CartPage;
import framework.pageObject.CheckoutPage;
import framework.pageObject.HomePage;
import framework.pageObject.InfoPage;
import framework.pageObject.LoginPage;
import resources.Base;

public class PageTests extends Base {
	WebDriver driver;
	HomePage hp;
	InfoPage ip;
	CartPage cp;
	CheckoutPage ckp;

	@BeforeTest
	public void setUp() throws IOException {
		driver = initialiseDriver();
		driver.get(prop.getProperty("url"));
		LoginPage lp = new LoginPage(driver);
		hp = lp.loginToHomePage(prop.getProperty("username"), prop.getProperty("password"));
		log.info("Logged in");
	}

	@Test
	public void checkDropdown() {
		Select s = new Select(hp.dropDown());
		s.selectByIndex(2);
		log.debug("Selected dropdown");
	}

	@Test
	public void selectItems() {
		List<WebElement> list = hp.items();
		List<String> itemNames = hp.itemNamesRequired();
		for (int i=0; i<list.size();i++) {
			String itemName = list.get(i).getText().toLowerCase();
			if(itemNames.contains(itemName)) {
				hp.addToCart().get(i).click();
				log.debug(itemName+" added to cart");
			}
		}
	}
	
	
	@Test(dependsOnMethods = "selectItems")
	public void addItems() {
		cp = hp.cart();
		ip = cp.getCheckOutBtn();
		log.debug("Checked out");
		ip.firstName().sendKeys(prop.getProperty("firstname"));
		ip.lastName().sendKeys(prop.getProperty("lastname"));
		ip.zipCode().sendKeys(prop.getProperty("pincode"));
		ckp = ip.continueBtn();
		log.debug("Landed on check out page");
	}
	
	@Test(dependsOnMethods = "addItems")
	public void checkOut() {
		double itemCost = 0;
		List<WebElement> prices = ckp.itemPrices();
		for(WebElement el: prices) {
			StringBuffer sb= new StringBuffer(el.getText().split("$")[0]);
			sb.deleteCharAt(0);
			itemCost = itemCost + Double.parseDouble(sb.toString());
		}
		log.info("Calculated cost is: "+itemCost);
		StringBuffer sb= new StringBuffer(ckp.itemTotal().getText().split(":")[1].trim());
		sb.deleteCharAt(0);
		double total = Double.parseDouble(sb.toString());
		log.info("Actual cost is: "+total);
		Assert.assertEquals(total, itemCost);
		log.debug("Prices match");
		//Assert.assertTrue(false);
		ckp.finishBtn().click();
		log.debug("Checked out");
		
	}
	
	

	@AfterTest
	public void tearDown() {
		driver.close();
		log.info("Closed browser");
	}

}
