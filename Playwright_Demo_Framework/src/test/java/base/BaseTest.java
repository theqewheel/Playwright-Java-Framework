package base;

import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import framework.config.ConfigManager;
import framework.utils.LoggerUtil;

public class BaseTest {
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	protected Logger logger;
	protected String baseURL = "https://automationexercise.com/";
	

	@BeforeMethod
	@Parameters({"environment"})
	
	public void setup(@Optional String envFromXML) {
		
		logger = LoggerUtil.getLogger(this.getClass());
		
		playwright = Playwright.create();
		
		//Set the Test-ID attribute
		playwright.selectors().setTestIdAttribute(ConfigManager.getProperty("test-id"));
		
		switch (ConfigManager.getProperty("browser").toLowerCase()) {
			case "chrome":
				browser = playwright.chromium()
						.launch(new BrowserType
								.LaunchOptions()
								//.setChannel("chrome")
								.setHeadless(Boolean.parseBoolean(ConfigManager.getProperty("headless")))
								.setSlowMo(1000));
				break;
			case "firefox":	
				browser = playwright.firefox()
						.launch(new BrowserType
								.LaunchOptions()
								.setHeadless(Boolean.parseBoolean(ConfigManager.getProperty("headless")))
								.setSlowMo(1000));
				break;
			case "webkit":
				browser = playwright.webkit()
						.launch(new BrowserType
								.LaunchOptions()
								.setChannel("webkit")
								.setHeadless(Boolean.parseBoolean(ConfigManager.getProperty("headless")))
								.setSlowMo(1000));
				break;
				
			default:
				logger.error("Unsupported browser specified in config.properties: " + ConfigManager.getProperty("browser"));
				logger.info("Supported browsers are: chrome, firefox, webkit");
				logger.error("Runtime Excepton:",new RuntimeException("Unsupported browser specified in config.properties: " + ConfigManager.getProperty("browser")));
		}
		
		
		page = browser.newPage();
		logger.info("Browser launched successfully");
		page.navigate(ConfigManager.getBaseURL(envFromXML));
		logger.info("Navigated to base URL: " + baseURL);

	}

	@AfterMethod
	public void teardown() {
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
		logger.info("This is the teardown method");
	}

}
