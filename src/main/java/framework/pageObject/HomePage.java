package framework.pageObject;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver;
	
	private By dropDown = By.cssSelector(".product_sort_container");
	private By shoppingCart = By.cssSelector(".shopping_cart_link");
	
	private By items = By.cssSelector(".inventory_item_name");
	private By addToCart = By.xpath("//button[contains(text(),'Add to cart')]");
	
	String [] itemNames = {"sauce labs onesie","sauce labs backpack"};
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}

	public WebElement dropDown() {
		return driver.findElement(dropDown);
	}
	
	public CartPage cart() {
		driver.findElement(shoppingCart).click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public List<WebElement> items() {
		return driver.findElements(items);
	}
	
	public List<WebElement> addToCart() {
		return driver.findElements(addToCart);
	}
	
	
	public List<String> itemNamesRequired() {
		List<String> itemsToBeAdded = Arrays.asList(itemNames);
		return itemsToBeAdded;
	}
	

}
