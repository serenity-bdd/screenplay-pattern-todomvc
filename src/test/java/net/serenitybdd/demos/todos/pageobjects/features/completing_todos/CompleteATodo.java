package net.serenitybdd.demos.todos.pageobjects.features.completing_todos;

import net.serenitybdd.demos.todos.pageobjects.steps.TodoUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
@WithTag("PageObjects pattern")
public class CompleteATodo {

    @Managed WebDriver driver;

    @Steps   TodoUserSteps james;

    @Test
    public void should_be_able_to_complete_a_todo() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.completes("Walk the dog");

        james.should_see_that_that_following_item_is_marked_as_complete("Walk the dog");

        james.should_see_that_the_number_of_items_left_is(1);
    }

    @Test
    public void should_see_the_number_of_todos_decrease_when_an_item_is_completed() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.completes("Walk the dog");

        james.should_see_that_the_number_of_items_left_is(1);
    }
}