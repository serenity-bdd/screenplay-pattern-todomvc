package net.serenitybdd.demos.todos.pageobjects.maintain_my_todo_list;

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
public class ClearCompletedTodos {

    @Managed WebDriver driver;

    @Steps(shared = true)
    TodoUserSteps james;

    @Test
    public void should_be_able_to_clear_completed_todos_with_page_objects() {

        try {
            james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

            james.completes("Walk the dog");

            james.clears_completed_items();

            james.should_see_that_displayed_items_contain("Put out the garbage");
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void should_not_be_able_to_clear_completed_todos_if_none_are_complete_with_page_objects() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.should_see_that_the_clear_completed_items_option_is_not_visible();
    }
}
