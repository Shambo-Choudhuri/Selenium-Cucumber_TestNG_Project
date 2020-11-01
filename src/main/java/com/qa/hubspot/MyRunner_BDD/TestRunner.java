package com.qa.hubspot.MyRunner_BDD;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;


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
	plugin = "json:target/cucumber-reports/CucumberTestReport.json"
	
	
)

//Don't Change the below code...This is fixed.....

public class TestRunner 
{
	private TestNGCucumberRunner testNGCucumberRunner;
	 
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception 
    {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    
    //This data provider is not the usual data provider which we use for reading data from Excel Utils class using Apache POI.
    //This data provider is used to fetch the feature files that are to be executed
    //Nothing related to test data
   
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) 
    {
    	//Run the feature files sequentially
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
    
    @DataProvider
    public Object[][] features() 
    {
        return testNGCucumberRunner.provideFeatures();
        //Fetch and return the feature files that are to be executed.
        
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception 
    {
        testNGCucumberRunner.finish();
    }
    
    
    

}
