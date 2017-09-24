package uiAutomation;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;

public class TC02_Another_Test extends TestBase{

	@BeforeClass
	public void beforeTestStart() throws IOException{
		setup();
	}
	
	@Test
	public void TC02_Another_Test_Method(){
		System.out.println("TC02_Another_Test");
	}
}
