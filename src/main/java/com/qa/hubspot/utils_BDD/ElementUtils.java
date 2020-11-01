package com.qa.hubspot.utils_BDD;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.google.common.base.Function;
import com.qa.hubspot.base_BDD.BasePage_BDD;

public class ElementUtils extends BasePage_BDD  {
	
	WebDriver driver;
	JavaScriptUtility jUtil;
	
	public ElementUtils(WebDriver driver)
	{
		this.driver=driver;
		this.jUtil=new JavaScriptUtility();
	}
	
	public String getCurrentUrl(WebDriver driver)
	{
		return(driver.getCurrentUrl());
	}
	
	/*
	 * Launch the specified URL 
	 */
	public void launchURLUsingGET(WebDriver driver,String URL)
	{
		driver.get(URL);
	}
	
	public void launchURLUsingNavigate(WebDriver driver,String URL)
	{
		driver.navigate().to(URL);
	}
	
	/*
	 * Return the Page Title
	 */
	
	public String getPageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	
	/*
	 * Click on a particular WebElement using a specific locator
	 */
	
	//@Parameters({"env"})
	public void clickOn(WebDriver driver,By loginButton)
	{
		WebElement element=getElement(driver,loginButton);
		
		if(init_prop().getProperty("highlight").equalsIgnoreCase("yes"))
		{
			jUtil.flash(element, driver);
		}
		
		element.click();
	}
	
	/*
	 * Quit the Browser or driver session
	 */
	
	public void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
	/*
	 * Refresh the browser
	 */
	
	public  void refreshBrowser(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	
	/*
	 * Back and Forward operations
	*/
	
	public  void forwardBrowser(WebDriver driver)
	{
		driver.navigate().forward();
	}
	
	public  void backwardBrowser(WebDriver driver)
	{
		driver.navigate().back();
	}
	
	/*
	 * Accept the Alert
	 */
	public  String acceptAlert(WebDriver driver)
	{
		Alert alert=driver.switchTo().alert();
		String getTextOfAlert=alert.getText();
		alert.accept();
		return getTextOfAlert;
	}
	
	/*
	 * Dismiss the Alert
	 */
	
	public  String dismissAlert(WebDriver driver)
	{
		Alert alert=driver.switchTo().alert();
		String getTextOfAlert=alert.getText();
		alert.dismiss();
		return getTextOfAlert;
	}
	
	/*
	 *  Return the Page Source of the current window
	 */
	
	public  String getPageSource(WebDriver driver)
	{
		return driver.getPageSource();
	}
	
	/*
	 *  Clears the Value
	 */
	
	public  void clearText(WebDriver driver,By locator)
	{
		getElement(driver,locator).clear();
	}
	
	/*
	 * Send value to a TextBox/EditBox
	 */
	
	//@Parameters({"env"})
	public  void sendText(WebDriver driver,By locator,String str)
	{
		WebElement element=getElement(driver,locator);
		
		if(init_prop().getProperty("highlight").equalsIgnoreCase("yes"))
		{
			jUtil.flash(element, driver);
		}
		
		element.sendKeys(str);
	}
	
	/*
	 *  Return a single WebElement
	 */
	
	public  WebElement getElement(WebDriver driver,By locator)
	{
		return driver.findElement(locator);
	}
	
	
	/*
	 * Maximize the Browser
	 */
	
	public  void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
		
	}
	
	public  Set<String> getWindowHandles(WebDriver driver)
	{
		return driver.getWindowHandles();
	}
	
	public  String getWindowHandle(WebDriver driver)
	{
		return driver.getWindowHandle();
	}
	
	public  void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	public  String getAttributeValueOfElement(WebDriver driver,By locator,String attribute)
	{
		return(getElement(driver,locator).getAttribute(attribute));
	}	
	
	public  void selectItemFromDropdownUsingSelectClass(WebElement element,String text)
	{
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}
	
	public  List<WebElement> getMultipleElements(WebDriver driver,By locator)
	{
		return driver.findElements(locator);
	}
	
	public  void selectItemFromDropdownWithoutUsingSelectClass(WebDriver driver,String text,By locator)
	{
		List<WebElement> elements=getMultipleElements(driver,locator);
		
		System.out.println("Total Number of Elements = " +elements.size());
		
		Iterator<WebElement> itr=elements.iterator();
		
		while(itr.hasNext())
		{
			WebElement element=itr.next();
			String elementText=element.getText();
			
			if(elementText.equals(text))
			{
				element.click();
			}
		}
		
	}
	
	public  void handleFramesUsingFrameName(WebDriver driver,String frameName) throws InterruptedException
	{
		driver.switchTo().frame(frameName);
		Thread.sleep(3000);
		
	}
	
	public  void handleFramesUsingFrameIndex(WebDriver driver,int frameIndex) throws InterruptedException
	{
		driver.switchTo().frame(frameIndex);
		Thread.sleep(3000);
		
	}
	
	public  void handleFramesUsingFrameElement(WebDriver driver,WebElement frameElement) throws InterruptedException
	{
		driver.switchTo().frame(frameElement);
		Thread.sleep(3000);
		
	}
	
	public  void switchToParentWindowFromFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	public  void mouseHoverOperation(WebDriver driver,WebElement target)
	{
		Actions action=new Actions(driver);
		action.moveToElement(target).build().perform();
	}
	
	public  void DragAndDropOperation(WebDriver driver,WebElement source,WebElement target)
	{
		Actions action=new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
	}
	
	public  void RightClickOperation(WebDriver driver,WebElement target)
	{
		Actions action=new Actions(driver);
		action.contextClick(target).build().perform();
	}
	
	public  void ActionsClassSendKeysOperation(WebDriver driver,WebElement target,String value)
	{
		Actions action=new Actions(driver);
		action.sendKeys(target,value).build().perform();
	}
	
	public  void ActionsClassClickOperation(WebDriver driver,WebElement target)
	{
		Actions action=new Actions(driver);
		action.click(target).build().perform();
	}
	
		
	public  void ImplicitlyWait(WebDriver driver,long TotalWaitingTime)
	{
		driver.manage().timeouts().implicitlyWait(TotalWaitingTime,TimeUnit.SECONDS);
	}
	
	public  void ExplicityWaitForElementToBeClickable(WebDriver driver,WebElement element,long TotalWaitingTime)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,TotalWaitingTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		
	}
	
	
	
	public  void PageLoadTimeOut(WebDriver driver,long TotalWaitingTime)
	{
		driver.manage().timeouts().pageLoadTimeout(TotalWaitingTime, TimeUnit.SECONDS);
	}
	
	public  void deleteAllCookies(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
	}

	public WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}
	
	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebElement element = getElement(driver, locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}	
	
	public Alert waitForAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}
	
	public List<WebElement> visibilityofAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	
	public  WebElement useFluentWait(WebDriver driver,long TotalWaitingTime,long pollingPeriodTime,final By locator)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
							.withTimeout(TotalWaitingTime,TimeUnit.SECONDS)
							.pollingEvery(pollingPeriodTime, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class);
		
		WebElement element=wait.until(new Function<WebDriver,WebElement>()
		{

			@Override
			public WebElement apply(WebDriver driver) 
			{
				// TODO Auto-generated method stub
				return driver.findElement(locator);
			}
		
		});
		
		return element;
		
	}
	
	//The above Fluent Wait can be written like below as well
	
	public  WebElement useFluentWaitDifferently(WebDriver driver,long TotalWaitingTime,long pollingPeriodTime,final By locator)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
							.withTimeout(TotalWaitingTime,TimeUnit.SECONDS)
							.pollingEvery(pollingPeriodTime, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class);
		
		 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	//Custom Wait
	
	public  WebElement customWait(WebDriver driver,By locator,long TotalWaitingTime) throws InterruptedException
	{
		WebElement element=null;
		
		for(int i=1;i<=TotalWaitingTime;i++)
		{
			try
			{
				element=getElement(driver, locator);
				break;
			}
			catch(Exception e)
			{
				System.out.println("Waiting for Element to be present on the page " +i +"secs");
				Thread.sleep(1000);
			}
		}
		
		if(element==null)
		{
			throw new NoSuchElementException("Element Not Found");
		}
		
		else
		{
			return element;
		}
		
		
	}
	
	public  void handleJQueryDropdown(WebDriver driver,By locator,String... value)
	{
		List<WebElement> elements=driver.findElements(locator);
		
		Iterator<WebElement> itr=elements.iterator();
		
		int c=0;

		if(value[0].equalsIgnoreCase("ALL"))
		{
			while(itr.hasNext())
			{
				itr.next().click();
			}
		}
		else
		{
			while(itr.hasNext())
			{
				WebElement element=itr.next();
				
				for(String value1:value)
				{
					if(element.getText().equalsIgnoreCase(value1))
					{
						element.click();
						c++;
						break;
					}
				}
				
			
				if(c==value.length)
				{
					break;
				}
			
			}
		}
		
	}

	


	public void ExplicitWaitForPageTitle(WebDriver driver,String title,long TotalWaitingTime)
	{
		WebDriverWait wait=new WebDriverWait(driver,TotalWaitingTime);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	
	public boolean waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public WebElement ExplicityWaitForVisibilityOfElement(WebDriver driver, By locator, int timeout) 
	{
		WebElement element = getElement(driver, locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
		
	}

}


	
	
			


