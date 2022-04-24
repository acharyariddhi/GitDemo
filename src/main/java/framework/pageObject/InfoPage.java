package framework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InfoPage {
	
	WebDriver driver;
	public InfoPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By firstName = By.id("first-name");
	private By lastName = By.id("last-name");
	private By zipCode = By.id("postal-code");
	private By continueBtn = By.id("continue");
	private By cancel = By.id("cancel");
	
	
	
	
	

	public WebElement firstName() {
		return driver.findElement(firstName);
	}
	
	public WebElement lastName() {
		return driver.findElement(lastName);
	}
	
	public WebElement zipCode() {
		return driver.findElement(zipCode);
	}
	
	public CheckoutPage continueBtn() {
		driver.findElement(continueBtn).click();
		CheckoutPage cp = new CheckoutPage(driver);
		return cp;
	}
	
	public WebElement cancel() {
		return driver.findElement(cancel);
	}
	


}
