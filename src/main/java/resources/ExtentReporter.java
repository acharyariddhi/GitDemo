package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter extends Base{
	public static ExtentReports extent;
	
	public static ExtentReports ExtentReportsNG() {
		
		String path = System.getProperty("user.dir")+"//reports//extentReports.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setDocumentTitle("Automation Reports");
		report.config().setReportName("Sauce demo reports");
		
		extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Riddhi");
		return extent;
	}

}
