package framework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By checkOut = By.id("checkout");
	private By continueShopping = By.id("continue-shopping");
	
	
	
	

	public InfoPage getCheckOutBtn() {
		driver.findElement(checkOut).click();
		InfoPage ip = new InfoPage(driver);
		return ip;
	}
	
	public WebElement continueShopping() {
		return driver.findElement(continueShopping);
	}
	


}
