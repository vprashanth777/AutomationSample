package cucumber.Framework.PageObjectModel;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.Framework.StepDefinations.Initialization;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GQA_HomePage extends Initialization {
	
	 @FindBy(xpath="//span[contains(text(),'Not Yet Competent')]")
	 List<WebElement> Competent;
	 
	 @FindBy(xpath="//a[@href='/courseunitDetails/CPC31511/CPCCCA3022A/118']")
	 WebElement Course;
	 
	 @FindBy(xpath="//span[contains(text(),'Qualification')]")
	 WebElement Qualification;
	 
	 @FindBy(xpath="//img[@src='/public/images/PROFILE.png']")
	 WebElement LogoutImg;
	
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	 WebElement Logout;
	
	public GQA_HomePage()
	{
		PageFactory.initElements(driver, this);
	}

	@Then("^User should able to login to the application$")
	public void user_should_able_to_login_to_the_application() throws Throwable {
		System.out.println(driver.getTitle());
		if(!driver.getTitle().contentEquals("GQ - RPL Qualification"))
		{
			Assert.fail("User Login Verification is Failed ,Browser title : " +driver.getTitle());
		}
		
	}


@When("^User should navigate to qualification Page$")
public void user_should_navigate_to_qualification_Page() throws Throwable {
	if(WD.until(ExpectedConditions.elementToBeClickable(Qualification)) != null)
	{
		Qualification.click();
	}
	
	else
		Assert.fail("User unable to click on the qualification  ");
	
 
}

@When("^User must have \"([^\"]*)\" under ELECTIVE UNITS$")
public void user_must_have_under_ELECTIVE_UNITS(String arg1) throws Throwable {
	if(Competent.size()>0)
	{
		System.out.println("There are " + Competent.size() +" number of Not Yet Competent cources are present");
	}
	else
	{
		Assert.fail("There are no Not Yet Competent cources are present");
	}
}

@When("^Select the \"([^\"]*)\" course$")
public void select_the_course(String arg1) throws Throwable {
	Course.click();
}


@Then("^User logout from application$")
public void user_logout_from_application() throws Throwable {
	
	
	Thread.sleep(2000);
	try{
	LogoutImg.click();
	Logout.click();
	}
	catch(Exception e)
	{
		
	}

	
	
}

@Given("^User  navigate to the application google URL$")
public void user_navigate_to_the_application_google_URL() throws Throwable {

    driver.get("https://google.com");
}
}
