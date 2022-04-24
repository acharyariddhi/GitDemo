package framework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	
	private By email = By.id("user-name");
	private By pwd = By.id("password");
	private By loginBtn = By.id("login-button");
	private By errorBtn = By.cssSelector("h3[data-test='error']");
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		return driver.findElement(pwd);
	}
	
	public WebElement loginButton() {
		return driver.findElement(loginBtn);
	}
	
	public String errorMsg() {
		return driver.findElement(errorBtn).getText();
	}
	
	public HomePage loginToHomePage(String username, String password) {
		driver.findElement(email).sendKeys(username);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(loginBtn).click();
		HomePage hp = new HomePage(driver);
		return hp;
	}
	

}
