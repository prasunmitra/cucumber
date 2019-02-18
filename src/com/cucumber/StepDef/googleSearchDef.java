package com.cucumber.StepDef;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.testUtility.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class googleSearchDef {
	
	public static WebDriver driver;
	PropertiesFileReader obj = new PropertiesFileReader();
	
	@Given("^Open Chrome browser and enter url$")
	public void openChromeBrowserAndEnterUrl() throws Throwable
	{
		Properties properties = obj.getProperty();
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nupur Saha\\workspace\\CucumberBDDFramework\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(properties.getProperty("browser.baseUrl"));
		Thread.sleep(3000);
	}
	
	
	@When("^Enter Search Criteria$")
	public void enterSearchCriteria() throws Throwable
	{
		driver.findElement(By.name("q")).sendKeys("Hi Cucumber");
		Thread.sleep(1500);
		
	}
	
	@Then("^Enter Search button$")
	public void enterSearchButton() throws Throwable
	{
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
}
