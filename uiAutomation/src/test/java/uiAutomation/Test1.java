package uiAutomation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utilities.TestUtil;

public class Test1 {
WebDriver driver;
	@Test
	public void test(){
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		
		if(1==1){
			Assert.assertTrue(false);
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result){
		getResult(result);
	}

	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS){
			System.out.println("passed");
		}else if(result.getStatus()==ITestResult.FAILURE){
			System.out.println("fail");
			String screen = TestUtil.captureScreen(driver,result);
			System.out.println(screen);
		}
		
	}
}
