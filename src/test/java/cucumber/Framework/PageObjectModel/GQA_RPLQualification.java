package cucumber.Framework.PageObjectModel;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.Framework.StepDefinations.Initialization;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class GQA_RPLQualification extends Initialization {
	String Title;
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
	@FindBy(xpath="//i[contains(text(),'add')]")
	 WebElement AddEvidence;
	
	@FindBy(id="select_existing_evidence")
	WebElement Evidance;
	
	@FindBy(name="evidence-file[text][]")
	WebElement EvidanceFile;
	
	@FindBy(xpath="//input[@value='Save']")
	 WebElement Save;
	@FindBy(xpath="//i[contains(text(),'close')]")
	WebElement EvidanceClose;
	
	@FindBy(id="selfassnote")
	WebElement AssessementNotes;
	
	@FindBy(id="btn-submit")
	WebElement Submit;
	
	@FindBy(xpath="//div[@class='modal-body']")
	WebElement SucessMessage;
	
	@FindBy(xpath="//button[contains(@onclick,'javascript:location.reload()')]")
	WebElement buttonOK;
	
	@FindBy(xpath="//span[contains(.,'Submitted ')]")
	WebElement SubmitStatus;
	
	public GQA_RPLQualification() {
		 PageFactory.initElements(driver, this);
		
	}

@Then("^New page should open with the below details$")
public void new_page_should_open_with_the_below_details(List<String> Elements) throws Throwable {
	for(String  Window:driver.getWindowHandles())
	{
		 if(!Window.equals(winHandleBefore))
		   {
		     driver.switchTo().window(Window);
		   }
	}
	
	Thread.sleep(2000);
	Title=driver.getTitle();
	for (String number : Elements) {
		String XPH="//strong[contains(text(),'"+number.trim()+"')]";
		if(driver.findElement(By.xpath(XPH)).isDisplayed())
		{
			Assert.assertTrue("User is able to see the details of "+number+" in the "+Title+"Page", driver.findElement(By.xpath(XPH)).isDisplayed());
		}
		
		else
		{
			Assert.fail("User is not able to see the details of "+number+" in the "+Title+"Page");
		}
    }
}

@Then("^upload the document from previously upload files with \"([^\"]*)\" AssessementNotes$")
public void upload_the_document_from_previously_upload_files_with_AssessementNotes(String arg1) throws Throwable {
	AddEvidence.click();
	Evidance.click();
	EvidanceFile.click();
	Save.click();
	EvidanceClose.click();
	Thread.sleep(3000);
	driver.navigate().refresh();
	Thread.sleep(3000);
	AssessementNotes.clear();
	Thread.sleep(3000);

	for(int i=1;i<=Integer.parseInt(arg1);i++)

	{
		AssessementNotes.sendKeys("Test ");
	}
	
}

@When("^User submit the evidance$")
public void user_submit_the_evidance() throws Throwable {
	//Submit.click();
	
	executor.executeScript("arguments[0].click();", Submit);
	//WD.until(ExpectedConditions.visibilityOf(SucessMessage));
	
	Thread.sleep(3000);
	executor.executeScript("arguments[0].click();", buttonOK);
	
	Thread.sleep(3000);
	
}

@Then("^status must be changed to satisfactory$")
public void status_must_be_changed_to_satisfactory() throws Throwable {
	if(SubmitStatus.isDisplayed())
	{
		Assert.assertTrue("User is able to upload the file and status is  got changed to Submitted ",true); 
	}
	
	else
	{
		Assert.fail("User is able to upload the file and status is not changed  to Submitted ");
	}
	
	for(String  Window:driver.getWindowHandles())
	{
		 if(!Window.equals(winHandleBefore))
		   {
		     driver.switchTo().window(Window).close();
		   }
	}
	driver.switchTo().window(winHandleBefore);

}


}
