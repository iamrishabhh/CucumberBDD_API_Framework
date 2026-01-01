package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/features",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-html-report.html",
        }
)
public class TestRunner {
}
