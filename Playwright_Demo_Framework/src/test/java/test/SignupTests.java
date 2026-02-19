package test;

import java.util.UUID;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ae.HomePage;
import pages.ae.SignupDetailPage;
import pages.ae.SignupLoginPage;

public class SignupTests extends BaseTest{
	
	//constants
	static String app_Base_URL = "https://automationexercise.com/";
	String username = "Autobot_" + System.currentTimeMillis();
	String email = "Autobot_" + System.currentTimeMillis() + "@cloudyfolk.com";
	String password = "@Pwd"+ UUID.randomUUID().toString().substring(0, 5);
	
	
	@Test(description = "AE001_TC01 - Verify that a new user can sign up successfully"
			,groups = {"smoke", "regression"})
	public void Test_AE001_TC01_Verify_New_User_SignUp() {
		
		HomePage homePage = new HomePage(page);
		SignupLoginPage signupLoginPage = new SignupLoginPage(page);
		SignupDetailPage signupDetailPage = new SignupDetailPage(page);
	
		//Step-1 Navigate to the URL
		logger.info("Running:Step-1");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		

		//Step-2 Navigate to Signup Page	
		logger.info("Running:Step-2");
		homePage.clickSignupLoginLink();
		signupLoginPage.verifyPageLoaded("/login", "Signup");
		signupLoginPage.verifyPageHeader("signup", "New User Signup!");
		
		//Step-3 Enter sign-up details and submit the form
		logger.info("Running:Step-3");
		signupLoginPage.EnterSignupDetails(username, email);
		signupLoginPage.ClickSignup();
		signupDetailPage.verifyPageLoaded("/signup", "Signup");
		signupDetailPage.verifyTextMessageDisplayed("Account Information", false);
		signupDetailPage.verifyTextMessageDisplayed("Address Information", true);
		signupDetailPage.verifyAutoPopulatedNameAndEmail(username, email);
		
		//Step-4 Enter the Account information
		logger.info("Running:Step-4");
		signupDetailPage.selectGender("male");
		signupDetailPage.enterPassword(password);
		signupDetailPage.selectDateOfBirth("10", "May", "1990");
		signupDetailPage.optNewsLetter(true);
		signupDetailPage.optSpecialOffers(false);
		
		//Step-5 Enter the Address information
		logger.info("Running:Step-5");
		signupDetailPage.enterFirstAndLastName(username, "bot");
		signupDetailPage.enterCompany("CloudyFolk");
		signupDetailPage.enterAddress("House No#33, Indira Nagar");
		signupDetailPage.enterAddress2("Near Velammal School");
		signupDetailPage.selectCountry("India");
		signupDetailPage.enterState("Tamil Nadu");
		signupDetailPage.enterCity("Pollachi");
		signupDetailPage.enterZipcode("600123");
		signupDetailPage.enterMobileNumber("90123456789");
		
		//Step-6 Create Account and verify that account is created successfully
		logger.info("Running:Step-6");
		signupDetailPage.clickCreateAccount();
		signupDetailPage.verifyTextMessageDisplayed("Account Created", false);
		signupDetailPage.ClickContinue();
		homePage.verifyTextMessageDisplayed("Logged in as " + username, true);
		homePage.DeleteAccount();
		homePage.verifyTextMessageDisplayed("Account Deleted", false);
		homePage.verifyTextMessageDisplayed("permanently deleted", false);
		homePage.ClickContinue();	
		
	}
}
