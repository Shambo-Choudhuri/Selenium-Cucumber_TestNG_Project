package com.qa.hubspot.utils_BDD;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	public Properties prop;
	public ChromeOptions chromeOptions;
	public FirefoxOptions firefoxOptions;
	
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		chromeOptions=new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			chromeOptions.addArguments("--headless");
			
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			chromeOptions.addArguments("--incognito");
		}
		
		
		return chromeOptions;
	}
	
	public FirefoxOptions getFirefoxOptions()
	{
		firefoxOptions=new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			firefoxOptions.addArguments("--headless");
			
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			firefoxOptions.addArguments("--incognito");
		}
		
		return firefoxOptions;
	}

}
