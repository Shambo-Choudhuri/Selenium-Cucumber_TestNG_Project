package com.qa.hubspot.StepDefinitionFiles_BDD;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.hubspot.utils_BDD.Constants;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePageStepDefinition {
	
	  private WebDriver driver;
	  
	  By Email=By.id("username"); 
	  By Password=By.id("password"); 
	  By LoginButton=By.id("loginBtn"); 
	
	@Given("^User is presently available on the Login Page$")
	public void user_is_presently_available_on_the_Login_Page()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(); driver.get("https://app.hubspot.com/login");
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleContains(Constants.LOGIN_PAGE_TITLE)); 
	}

	@When("^Login Page Title is HubSpot Login$")
	public void login_Page_Title_is_HubSpot_Login()
	{
		String pageTitle=driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertEquals(pageTitle,Constants.LOGIN_PAGE_TITLE); 
	}

	/*@Then("^User enters Username and Password for Login$")
	public void user_enters_Username_and_Password_for_Login(DataTable credentials)
	{
		List<List<String>> data=credentials.raw();
		driver.findElement(Email).sendKeys(data.get(0).get(0));
		driver.findElement(Password).sendKeys(data.get(0).get(1)); 
	}*/
	
	
	
	@Then("^User enters Username and Password for Login$")
	public void user_enters_Username_and_Password_for_Login(DataTable credentials)
	{
		List<Map<String, String>> data=credentials.asMaps(String.class,String.class);
		Iterator<Map<String, String>> itr=data.iterator();
		while(itr.hasNext())
		{
			Map<String,String> map=itr.next();
			Set<Entry<String, String>> s=map.entrySet();
			Iterator<Entry<String, String>> itr1=s.iterator();
			while(itr1.hasNext())
			{
				Map.Entry<String,String> m1=(Map.Entry<String,String>)itr1.next();
				switch(m1.getKey())
				{
					case "username":
							driver.findElement(Email).sendKeys(m1.getValue());
							break;
					case "password":
							driver.findElement(Password).sendKeys(m1.getValue());
							break;
				}
				
			}
			
			
		}
		
		/*for (Map<String, String> data : credentials.asMaps(String.class, String.class)) 
		{
			driver.findElement(Email).sendKeys(data.get("username"));
			driver.findElement(Password).sendKeys(data.get("password"));
		}*/
	}
	
	

	@Then("^User must click on the Login Button$")
	public void user_must_click_on_the_Login_Button() throws InterruptedException
	{
		driver.findElement(LoginButton).click(); 
		Thread.sleep(30000); 
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_TITLE)); 
	}

	@Then("^User should be navigated to the Home Page$")
	public void user_should_be_navigated_to_the_Home_Page()
	{
		String homePageTitle=driver.getTitle();
		Assert.assertEquals(homePageTitle,Constants.HOME_PAGE_TITLE);
	}

	@Then("^User should close the browser$")
	public void user_should_close_the_browser()
	{
	    driver.close();
	}



}
