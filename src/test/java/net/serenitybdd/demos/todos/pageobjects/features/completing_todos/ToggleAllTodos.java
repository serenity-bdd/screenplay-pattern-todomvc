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
public class ToggleAllTodos {

    @Managed WebDriver driver;

    @Steps   TodoUserSteps james;

    @Test
    public void should_be_able_to_quickly_complete_all_todos() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.toggles_all_items();

        james.should_see_that_that_following_items_are_marked_as_complete("Walk the dog", "Put out the garbage");
    }

    @Test
    public void should_be_able_to_toggle_status_of_all_todos() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.toggles_all_items();

        james.toggles_all_items();

        james.should_see_that_that_following_items_are_marked_as_active("Walk the dog", "Put out the garbage");
    }


    @Test
    public void should_see_that_there_are_zero_items_todo_when_all_are_toggled_complete() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.toggles_all_items();

        james.should_see_that_the_number_of_items_left_is(0);
    }

    @Test
    public void should_see_how_many_items_todo_when_all_are_toggled_to_incomplete() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.toggles_all_items();

        james.toggles_all_items();

        james.should_see_that_the_number_of_items_left_is(2);
    }
}