package net.serenitybdd.demos.todos.pageobjects.accessing_the_application;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.demos.todos.pageobjects.steps.TodoUserSteps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("PageObjects")
@Tag("version:RELEASE-3")
public class LearnAboutTheApplication {

    @Managed WebDriver driver;

    @Steps   TodoUserSteps james;

    @Test
    public void should_be_able_to_identify_the_application_with_page_objects() {

        james.starts_with_an_empty_todo_list();

        james.should_see_the_correct_website_title();
        james.should_see_the_correct_application_heading();
    }

    @Test
    public void should_see_how_to_begin_with_page_objects() {

        james.starts_with_an_empty_todo_list();

        james.should_see_that_the_placeholder_text_says("What needs to be done?");
    }
}
