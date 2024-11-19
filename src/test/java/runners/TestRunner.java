package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
        },
        features = "src/test/resources/features",//path from content root
        glue = "stepDefinitions",
        dryRun =false,
        tags ="@firstScenario"
)
public class TestRunner {
}
