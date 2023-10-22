package KasirAja.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/KasirAja/cucumber/features",
        glue =  "KasirAja.cucumber.stepDef",
        plugin = {"html:target/HTML_report.html"}
)

public class runLogin {

}