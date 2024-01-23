package net.serenitybdd.demos.todos.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.demos.todos.pageobjects.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pageobjects.steps.TodoUserSteps;
import net.serenitybdd.screenplay.Actor;

import java.util.List;

public class TodoUserActionSteps {

    @Steps
    TodoUserSteps james;

    @Given("Jane has prepared a todo list containing {items}")
    public void that_James_has_an_empty_todo_list(List<String> items) {
        james.starts_with_a_todo_list_containing(items.toArray(new String[]{}));
    }

    @And("he/she has completed the task called {string}")
    public void sheHasCompletedTheTaskCalled(String task) {
        james.completes(task);
    }

    @When("she filters the list to show only {} tasks")
    public void sheFiltersTheListToShowOnlyFilterTasks(String status) {
        james.filters_items_to_show(TodoStatusFilter.valueOf(status));
    }

    @Then("her list should contain {items}")
    public void herListShouldContain(List<String> items) {
        james.should_see_that_displayed_items_contain(items.toArray(new String[]{}));
    }
}
