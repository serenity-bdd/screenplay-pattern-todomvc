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
public class DeletingTodos {

    @Managed WebDriver driver;

    @Steps(shared = true)
    TodoUserSteps james;

    @Test
    public void should_be_able_to_delete_todos_with_page_objects() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.deletes("Walk the dog");

        james.should_see_that_displayed_items_contain("Put out the garbage");
    }

    @Test
    public void should_see_deleting_a_todo_decreases_the_remaining_items_count_with_page_objects() {

        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");

        james.deletes("Walk the dog");

        james.should_see_that_the_number_of_items_left_is(1);
    }
}
