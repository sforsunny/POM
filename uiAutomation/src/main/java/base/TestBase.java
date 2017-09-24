package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.TestUtil;

public class TestBase {
	public WebDriver driver;
	Properties prop = new Properties();
	ExtentReports extent;
	ExtentTest extentLogger;
	public static String fileSeperator = System.getProperty("file.separator");

	public void setup() throws IOException {
		initProperties();
		//TestUtil.createFolder("Report");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy");
		String reportName = "Automation_"+formater.format(calendar.getTime())+".html";
		File reportFile = new File(TestUtil.createFolder("Report") + fileSeperator + reportName);
		extent = new ExtentReports(reportFile.toString(), false);
		getURL();
	}

	public void getURL() {
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(prop.getProperty("url"));
		}

	}

	public void initProperties() throws IOException {
		File file = new File("E:\\Maven Project with eclipse\\Project\\uiAutomation\\config.properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
	}

	@BeforeMethod
	public void beforeMethod(Method result) {
		extentLogger = extent.startTest(result.getName());
		extentLogger.log(LogStatus.INFO, result.getName() + " test started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		getResult(result);
		extent.endTest(extentLogger);
		extent.flush();
	}

	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentLogger.log(LogStatus.PASS, result.getName() + " test passed");
			String screen = TestUtil.captureScreen(driver, result);
			extentLogger.log(LogStatus.PASS, extentLogger.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentLogger.log(LogStatus.FAIL, result.getName() + " test failed");
			String screen = TestUtil.captureScreen(driver, result);
			extentLogger.log(LogStatus.FAIL, extentLogger.addScreenCapture(screen));
		}

	}

	@AfterClass(alwaysRun = true)
	public void end() {
		driver.close();

	}
}
