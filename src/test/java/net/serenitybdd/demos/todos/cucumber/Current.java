package net.serenitybdd.demos.todos.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = {"@current"},
        features = "src/test/resources/features"
)
public class Current {}
