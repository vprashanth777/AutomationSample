package cucumber.Framework.StepDefinations;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.Framework.PageObjectModel.GQA_LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CommonStepDefinations extends Initialization {

	
	
	@Before
	public void BrowserSetup()
	{
		GetDriverObject();
		WD=new WebDriverWait(driver, 60);
		winHandleBefore= driver.getWindowHandle();
	}
	
	
	@After
	public void tearDown(Scenario scenario) {
	    if (scenario.isFailed()) {			
	      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			
	      scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
	    
	   }
	    driver.quit();
	    driver=null;
	}
	
	@Given("^User  navigate to the application URL$")
	public void Get_Application_URL() throws Throwable {
		 driver.get(GetPropertValue("URL")); 
		   WD.until(ExpectedConditions.titleContains("GQ - RPL Login"));	   
	}
	


	
	
}
