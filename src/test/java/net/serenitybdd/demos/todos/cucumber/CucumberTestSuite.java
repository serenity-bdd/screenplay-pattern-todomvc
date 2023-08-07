package net.serenitybdd.demos.todos.cucumber;

//import io.cucumber.junit.CucumberOptions;
//import net.serenitybdd.cucumber.CucumberWithSerenity;
//import org.junit.runner.RunWith;
//
//@RunWith(CucumberWithSerenity.class)
//@CucumberOptions(
//        features = "src/test/resources/features"
//)
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static org.junit.jupiter.engine.Constants.PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
                        value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:target/test-results/timeline")
public class CucumberTestSuite {
}
