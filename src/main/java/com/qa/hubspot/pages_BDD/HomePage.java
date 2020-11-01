package com.qa.hubspot.pages_BDD;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base_BDD.BasePage_BDD;
import com.qa.hubspot.utils_BDD.ElementUtils;

public class HomePage extends BasePage_BDD {
	
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	
	//Object Repository using By locator technique
	
	//HomePage class objects
	
	By headerText=By.cssSelector("div.private-header__title.private-page__title>h1");
	By MainMenuContactsLink=By.id("nav-primary-contacts-branch");
	By SubMenuContactsLink=By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		this.elementUtils=new ElementUtils(driver);
	}
	
	public String verifyHeaderText()
	{
		WebElement header=elementUtils.getElement(driver, this.headerText);
		
		if(header.isDisplayed())
		{
			return header.getText();
		
		}
		else
		{
			return "HeaderText is not displayed";
		}
		
	}
	
	public String verifyHomePageTitle()
	{
		return elementUtils.getPageTitle(driver);
	}
	
	
	

}
