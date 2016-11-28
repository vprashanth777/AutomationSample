package cucumber.Framework.PageObjectModel;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import cucumber.Framework.StepDefinations.Initialization;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GQA_LoginPage extends Initialization {
	AjaxElementLocatorFactory  factory;
	
	 @FindBy(id="InputEmail1")
	    WebElement UserName;
	 @FindBy(id="InputPassword1")
	    WebElement Password;
	 @FindBy(xpath="//input[contains(@value,'LOGIN')]")
	    WebElement LoginButton;
	
	 
	public GQA_LoginPage(){
		
        PageFactory.initElements(driver, this);
        

    }
	
	@Given("^Login into the application as \"([^\"]*)\"$")
	public void login_into_the_application_as(String arg1) throws Throwable {
		if(arg1.equalsIgnoreCase("Candidate"))
		{
		UserName.clear();
		UserName.sendKeys(GetPropertValue("CandidateUserName"));
		Password.clear();
		Password.sendKeys(GetPropertValue("CandidatePassword"));
		LoginButton.click();
		}
		
		else if(arg1.equalsIgnoreCase("Facilitator"))
		{
		UserName.clear();
		UserName.sendKeys(GetPropertValue("FacilitatorUserName"));
		Password.clear();
		Password.sendKeys(GetPropertValue("FacilitatorPassword"));
		LoginButton.click();
		}
		
		else if(arg1.equalsIgnoreCase("Assessor"))
		{
		UserName.clear();
		UserName.sendKeys(GetPropertValue("AssessorUserName"));
		Password.clear();
		Password.sendKeys(GetPropertValue("AssessorPassword"));
		LoginButton.click();
		}
	}

	
	public void setUserName(String strUserName){
		UserName.clear();
		UserName.sendKeys(strUserName);   
    }
	
	public void setPassword(String strPassword){
		Password.clear();
		Password.sendKeys(strPassword);   
    }
	
	public void ClickonLogin(){
		LoginButton.click();
		
    }


}
