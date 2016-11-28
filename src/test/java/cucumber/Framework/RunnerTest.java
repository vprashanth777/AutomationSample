package cucumber.Framework;




import org.junit.runner.RunWith;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		format = { "pretty", "html:target/cucumber","json:target/JSON/Output.json" },
		
		features ={ "FeatureFiles\\"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/CucumberReport.html","rerun:target/rerun.txt"},
        
        tags={"@Candidate"}
   

)


public class RunnerTest {
	
	
	
}


