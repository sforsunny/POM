package utilities;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import base.TestBase;

public class TestUtil extends TestBase{

	
	
	public static String captureScreen(WebDriver driver,ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String testClassName = getTestClassName(result);
		String testMethodName = getMethodName(result);
		String screenShotName = testMethodName +formater.format(calendar.getTime())+ ".png";

		try {
			
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testClassName+fileSeperator+screenShotName);
			File targetFile = new File(createFolder("Screenshots")+ fileSeperator + testClassName+fileSeperator+screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);
			return targetFile.toString();
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}
	
	public static String createFolder(String name){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy");
		File file = new File(System.getProperty("user.dir")+fileSeperator+ "Results and Screenshots"+fileSeperator+formater.format(calendar.getTime())+fileSeperator+ name);
		if (!file.exists()) {
			System.out.println("File created " + file);
			file.mkdir();
		}	
		return file.toString();
	}
	public static String getTestClassName(ITestResult result) {
		String testName = result.getInstanceName().trim();
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		return reqTestClassname[i];
	}
	
	public static String getMethodName(ITestResult result){
		return result.getName().toString().trim();
		
	}
}
