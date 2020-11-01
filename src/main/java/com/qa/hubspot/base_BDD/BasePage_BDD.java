package com.qa.hubspot.base_BDD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.utils_BDD.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

class InvalidBrowserException extends RuntimeException
{
	
}

public class BasePage_BDD 
{
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	//This method can be accessed by only one Thread at a time
	
	public synchronized WebDriver getDriverInstance()
	{
		driver=tlDriver.get();
		return driver; //Return the ThreadLocal WebDriver object
	}
	
		
		//Thread-Local WebDriver object with Browser Options
		
		public WebDriver init_driver(Properties prop) throws Exception
		{
			optionsManager=new OptionsManager(prop);
			
			switch(prop.getProperty("browser"))
			{
					case "chrome":
						WebDriverManager.chromedriver().setup();
						driver=new ChromeDriver(optionsManager.getChromeOptions());
						tlDriver.set(driver);
						System.out.println("Tests Running on chrome browser");
						break;
						
					case "firefox":
					    WebDriverManager.firefoxdriver().setup();
					    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));//driver=new FirefoxDriver();
					    System.out.println("Tests Running on firefox browser");
					    break;
					    
			    	default:
			    	throw new InvalidBrowserException();
			    	
			 }
					    
				return getDriverInstance(); //returning a thread safe and ThreadLocal WebDriver object
		}
	
	//Without Environment Configuration
	
		public Properties init_prop()
		{
			prop=new Properties();
			
			FileInputStream FIS;
			
			try 
			{
				FIS = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\hubspot\\config_BDD\\qa.config.properties");
				prop.load(FIS);
			} 
			catch(FileNotFoundException e) 
			{
				e.getMessage();
			} 
			catch(IOException e)
			{
				e.getMessage();
			}
				
				
			return prop;
			
		}
	
		
		//Take Screenshot
		
		public String getScreenshot()
		{
			//WebDriver driver=new ChromeDriver();
			
			//TakesScreenshot screenshot=new ChromeDriver();
			
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			
			//Take the Screenshot which returns a File class object
			
			File src=screenshot.getScreenshotAs(OutputType.FILE);
			
			//Copy the screenshot into a desired location in the project directory
			
			String path=System.getProperty("user.dir") + "\\screenshots"+"\\"+System.currentTimeMillis()+".png";
			
			File file=new File(path);
			
			try 
			{
				FileUtils.copyFile(src, file);
			} 
			catch (IOException e) 
			{
				e.getMessage();
			}
			
			return path;
		}
		
		
}
