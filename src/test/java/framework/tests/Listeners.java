package framework.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporter;

public class Listeners extends Base implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporter.ExtentReportsNG();
	ThreadLocal<ExtentTest> extentObj = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		extentObj.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		extentObj.get().log(Status.PASS, result.getName()+" has passed");
		try {
			extentObj.get().addScreenCaptureFromPath(getScreenshot(result.getName(), driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentObj.get().fail(result.getThrowable());
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			getScreenshot(result.getName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		try {
			extentObj.get().addScreenCaptureFromPath(getScreenshot(result.getName(),driver), result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	
}
