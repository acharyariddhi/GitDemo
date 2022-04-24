package framework.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By finish = By.id("finish");
	private By cancel = By.id("cancel");
	private By prices = By.cssSelector(".inventory_item_price");
	private By itemTotal = By.cssSelector(".summary_subtotal_label");
	
	
	

	public WebElement finishBtn() {
		return driver.findElement(finish);
	}
	
	public WebElement cancelBtn() {
		return driver.findElement(cancel);
	}
	
	public List<WebElement> itemPrices() {
		return driver.findElements(prices);
	}

	public WebElement itemTotal() {
		return driver.findElement(itemTotal);
	}

}
