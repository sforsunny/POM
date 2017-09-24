package uiAutomation;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;

public class TC01_Verify_Assign_Leave_Page extends TestBase{

	@BeforeClass
	public void beforeTestStart() throws IOException{
		setup();
	}
	
	@Test
	public void TC01_Verify_Assign_Leave_Page_Method(){
		System.out.println("TC01_Verify_Assign_Leave_Page");
	}
}
