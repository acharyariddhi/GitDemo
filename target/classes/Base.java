package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	public Logger log = LogManager.getLogger(Base.class.getName());
	
	
	
	public WebDriver initialiseDriver() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop = new Properties();
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		//String browserName = System.getProperty("browser"); //to send parameter from maven command
		//mvn test -Dbrowser=chrome
		
		
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);
		}
		
		else if(browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			driver = new EdgeDriver(options);
		}
		
		else  {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			driver = new FirefoxDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
		
	}
	
	public String getScreenshot(String screenshotName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"//reports//"+screenshotName+".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	

}
