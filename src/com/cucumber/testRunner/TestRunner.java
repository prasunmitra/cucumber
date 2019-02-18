package com.cucumber.testRunner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;



@CucumberOptions(
		features = "./features",
		glue = "com.cucumber.StepDef",
		tags = {"@UATTesting"},
		plugin = {"pretty", "html:target/site/cucumber-preety",
				"json:target/cucumber.json",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome = true)

public class TestRunner  {
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() throws Exception{
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}
	
	@Test(dataProvider = "features1")
	public void feature(CucumberFeatureWrapper cucumberFeature){
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}
	
	@DataProvider
	public Object[][] features1(){
		return testNGCucumberRunner.provideFeatures();
	}
	
	@AfterClass
	public void tearDownClass() throws Exception {
		Reporter.loadXMLConfig(new File("./extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Chrome");
        Reporter.setTestRunnerOutput("Sample test runner output message");
		testNGCucumberRunner.finish();
	}

}
