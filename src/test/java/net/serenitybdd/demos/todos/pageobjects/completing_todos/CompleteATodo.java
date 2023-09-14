package net.serenitybdd.demos.todos.pageobjects.completing_todos;

import net.serenitybdd.annotations.Steps;
import net.serenitybdd.demos.todos.pageobjects.steps.TodoUserSteps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("PageObjects")
@Tag("version:RELEASE-3")
public class CompleteATodo {

    @Steps
    TodoUserSteps james;

    @BeforeEach
    public void setup() {
        james.starts_with_a_todo_list_containing("Walk the dog", "Put out the garbage");
    }

    @Test
    public void should_be_able_to_complete_a_todo_with_page_objects() {

        james.completes("Walk the dog");

        james.should_see_that_that_following_item_is_marked_as_complete("Walk the dog");

        james.should_see_that_the_number_of_items_left_is(1);

    }

}
