package TestRunner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/java/features/placeValidations.feature"},
		plugin="json:target/jsonReports/cucumber-report.json",
		glue={"stepDefinations"})     //glue={"stepDefinations"},tags="@DeletePlace")
		                                                 //hooks class will execute only if you mention in tags
public class testRunner {

}
