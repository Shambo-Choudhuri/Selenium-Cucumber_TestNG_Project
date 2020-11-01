package com.qa.hubspot.MyRunner_BDD;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions 
(
	features="src\\main\\java\\com\\qa\\hubspot\\FeatureFiles_BDD",
	glue= {"com.qa.hubspot.StepDefinitionFiles_BDD"},
	monochrome=true,
	strict=true,
	dryRun=false,
	tags= {"~@Ignore"},
	format = {
            "pretty",
            "html:target/cucumber-reports/cucumber-pretty",
            "json:target/cucumber-reports/CucumberTestReport.json",
            "rerun:target/cucumber-reports/rerun.txt"
    		 },
	plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
	
	
)

public class TestRunner_ExtentReport extends AbstractTestNGCucumberTests
{
	@BeforeClass
	public static void setup()
	{
		ExtentProperties extentProperties=ExtentProperties.INSTANCE;
		extentProperties.setReportPath("output/myreport.html");
	}
	
	@AfterClass
	public static void tearDown()
	{
		Reporter.loadXMLConfig(new File("C:\\Users\\user\\Documents\\EclipseProjects\\DemoSeleniumSessionsWithBDDApproach_TestNG\\src\\test\\resources\\extent-config.xml"));
		Reporter.setSystemInfo("user",System.getProperty("user.name"));
		Reporter.setSystemInfo("os","Windows");
		Reporter.setTestRunnerOutput("Sample Test Runner Output Message");
	}
}
