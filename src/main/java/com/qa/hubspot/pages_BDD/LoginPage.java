package com.qa.hubspot.pages_BDD;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base_BDD.BasePage_BDD;
import com.qa.hubspot.utils_BDD.Constants;
import com.qa.hubspot.utils_BDD.ElementUtils;

public class LoginPage extends BasePage_BDD {
	
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	//Object Repository using By locator technique
	
		//Login Page class Objects
		
		By Email=By.id("username");
		By Password=By.id("password");
		By LoginButton=By.id("loginBtn");
		By signUpLink=By.linkText("Sign up");
		
		
		
		
		//Constructor for LoginPage class
		
		
		public LoginPage(WebDriver driver)
		{
			this.driver=driver;
			this.elementUtils=new ElementUtils(driver);
		}
		
		//Page Actions
		
		//Get the Login Page Title
		
		public String getLoginPageTitle()
		{
			elementUtils.ExplicitWaitForPageTitle(driver,Constants.LOGIN_PAGE_TITLE, 20);
			String title=elementUtils.getPageTitle(driver);
			return title;
		}
		
		//Check whether sign up link is present or not
		
		public boolean verifySignUpLink()
		{
			boolean verify=elementUtils.getElement(driver, signUpLink).isDisplayed();
			return verify;
		}
		
		public HomePage doLogin(String Email,String Password) throws InterruptedException
		{
			
			elementUtils.sendText(driver, this.Email, Email);
			elementUtils.sendText(driver, this.Password, Password);
			elementUtils.clickOn(driver, this.LoginButton);
			
			Thread.sleep(30000);
			elementUtils.ExplicitWaitForPageTitle(driver,Constants.HOME_PAGE_TITLE, 20);
			
			return new HomePage(driver);
		}
		

}
