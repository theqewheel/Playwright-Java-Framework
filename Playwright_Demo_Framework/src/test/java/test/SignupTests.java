package test;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.pages.ae.HomePage;

public class SignupTests extends BaseTest{
	
	//constants
	static String app_Base_URL = "https://automationexercise.com/";
	
	
	@Test(description = "AE001_TC01 - Verify that a new user can sign up successfully")
	public void Test_AE001_TC01_Verify_New_User_SignUp() {
		
		HomePage homePage = new HomePage(page);
	
		//Step-1 Navigate to the URL
		System.out.println("Running:Step-1");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		
	}
	
	

}
