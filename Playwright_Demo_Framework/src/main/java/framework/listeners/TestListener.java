package framework.listeners;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import framework.config.ConfigManager;
import framework.utils.LoggerUtil;

public class TestListener implements ITestListener, ISuiteListener {
	
	private static final Logger logger = LoggerUtil.getLogger(TestListener.class);
	
	/**
	 * ======================= SUITE LEVEL LISTENERS ======================
	 */
	
	/*
	 * This method is invoked before the SuiteRunner begins to run any tests in the suite.
	 */
	
	@Override
	public void onStart(ISuite suite) {
		
		ConfigManager.initializeEnvironment(suite);
		
		logger.info("================XXXXXXXXXXXXXX================");
		logger.info("Starting Suite: {}",suite.getName());
		logger.info("Execution Environment: {}",ConfigManager.getEnvironment());
		logger.info("==============================================");
	}
	
	/*
	 * This method is invoked after the SuiteRunner completes run of all tests in the suite.
	 */
	
	
	@Override
	public void onFinish(ISuite suite) {
		
		logger.info("==============================================");
		logger.info("Finished Suite: {}",suite.getName());
		logger.info("================XXXXXXXXXXXXXX================");
	}
	
	/**
	 * ======================= TEST LEVEL LISTENERS ======================
	 */
	
	/*
	 * This method is invoked before any Test begins in the suite.
	 */
	
	public void onTestStart(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		
		//Set MDC value for the logging pattern
		MDC.put("env", ConfigManager.getEnvironment());
		MDC.put("testname",testName);
		
		logger.info("-----------------  STARTING TEST : {}  -----------------" , testName);
	}
	
	
}
