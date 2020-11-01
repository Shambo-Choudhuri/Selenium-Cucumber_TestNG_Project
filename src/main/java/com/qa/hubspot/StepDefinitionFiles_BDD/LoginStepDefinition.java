package com.qa.hubspot.StepDefinitionFiles_BDD;

import java.util.Properties;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.hubspot.base_BDD.BasePage_BDD;
import com.qa.hubspot.pages_BDD.HomePage;
import com.qa.hubspot.pages_BDD.LoginPage;
import com.qa.hubspot.utils_BDD.Constants;
import com.qa.hubspot.utils_BDD.ElementUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


  public class LoginStepDefinition
  {
  
	  public BasePage_BDD basePage;
	  private WebDriver driver;
	  public ElementUtils elementUtils;
	  public Properties prop;
	  public LoginPage loginPage;
	  public HomePage homePage;
	  
  
	  By Email=By.id("username"); 
	  By Password=By.id("password"); 
	  By LoginButton=By.id("loginBtn"); 
  
  
  
  @Given("^User is currently present on the Login Page$") 
  public void user_is_currently_present_on_the_Login_page() throws Exception 
  { 
	  basePage=new BasePage_BDD();
	  prop=basePage.init_prop();
	  driver=basePage.init_driver(prop);
	  driver.get(prop.getProperty("url"));
	  elementUtils=new ElementUtils(driver);
	  elementUtils.ExplicitWaitForPageTitle(driver,Constants.LOGIN_PAGE_TITLE,30);
	  
	  
	   
  }
  
  @When("^Title of Login Page is HubSpot Login$") 
  public void title_of_Login_page_is_HubSpot_Login() 
  { 
	  loginPage=new LoginPage(driver);
	  String loginPageTitle=loginPage.getLoginPageTitle();
	  System.out.println(loginPageTitle);
	  Assert.assertEquals(loginPageTitle,Constants.LOGIN_PAGE_TITLE);
  }
  
  //Regular Expressions:Both are same...can use any one 
  
  //1. \"([^\"]*)\" 
  //2. \"(.*)\"
  
  /*@Then("^User enters \"(.*)\" and \"(.*)\"$") 
  public void user_enters_username_and_password() 
  {
	  /*driver.findElement(Email).sendKeys(username);
	  driver.findElement(Password).sendKeys(password);
  }*/
  
  @Then("^User enters username and password$") 
  public void user_enters_username_and_password() throws InterruptedException 
  {
	  
	  homePage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
  }
  
 
  @Then("^User lands on Home Page$") 
  public void user_lands_on_Home_Page() 
  {
	  String homePageTitle=homePage.verifyHomePageTitle();
	  Assert.assertEquals(homePageTitle,Constants.HOME_PAGE_TITLE);
  
  }
  
  @Then("^User quits the browser$")
  public void user_quits_the_browser()
  {
	  driver.quit();
  }

  
  
  
  
  
  
  
  
  
  
  }
 