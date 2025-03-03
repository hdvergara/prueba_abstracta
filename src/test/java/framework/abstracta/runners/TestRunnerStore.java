package framework.abstracta.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features/",
        glue = {"framework.abstracta.steps"},
        plugin = {"pretty",
                "html:target/cucumber-html-report/index.html",
                "json:target/cucumber.json"
        }
)
public class TestRunnerStore extends AbstractTestNGCucumberTests {
}
