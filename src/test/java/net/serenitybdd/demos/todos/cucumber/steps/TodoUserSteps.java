package net.serenitybdd.demos.todos.cucumber.steps;


import com.google.common.base.Splitter;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.demos.todos.cucumber.MissingTodoItemsException;
import net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.AddATodoItem;
import net.serenitybdd.demos.todos.screenplay.tasks.CompleteItem;
import net.serenitybdd.demos.todos.screenplay.tasks.FilterItems;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.*;

public class TodoUserSteps {

    @ParameterType(".*")
    public List<String> tasks(String value) {
        return Splitter.on(",").trimResults().splitToList(value);
    }

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return theActorCalled(actorName);
    }

    @ParameterType(".*")
    public TodoStatusFilter status(String statusName) {
        return TodoStatusFilter.valueOf(statusName);
    }

    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.aNewActor().wasAbleTo(Start.withAnEmptyTodoList());
    }

    @Given("that {actor} has an empty todo list")
    public void that_James_has_an_empty_todo_list(Actor theActor) throws Throwable {
        theActor.wasAbleTo(Start.withAnEmptyTodoList());
    }

    @Given("that {actor} has a todo list containing {tasks}")
    public void that_James_has_an_empty_todo_list(Actor theActor, List<String> items) throws Throwable {
        theActor.wasAbleTo(Start.withATodoListContaining(items));
    }

    @When("(s)he adds {string} to his/her/the list")
    public void adds_Buy_some_milk_to_his_list(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(AddATodoItem.called(item));
    }

    @Then("his/her/the todo list should contain {tasks}")
    public void todo_list_should_contain(List<String> expectedItems) throws Throwable {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(expectedItems))
                .orComplainWith(MissingTodoItemsException.class,"Missing todos " + expectedItems));
    }

    @Then("his/her/the todo list should be empty")
    public void todo_list_should_be_empty() throws Throwable {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(EMPTY_LIST)));
    }

    @Then("^s?he (?:completes|has completed) the task called \"(.*)\"$")
    public void completes_task_called(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(
                CompleteItem.called(item)
        );
    }

    @When("(s)he filters her list to show only {status} tasks")
    public void filters_tasks_by(TodoStatusFilter status) {
        theActorInTheSpotlight().attemptsTo(FilterItems.toShow(status));
        withCurrentActor(
                FilterItems.toShow(status)
        );
    }

    @Then("{actor}'s todo list should contain {tasks}")
    public void a_users_todo_list_should_contain(Actor theActor, List<String> expectedItems) throws Throwable {
        theActor.should(seeThat(TheItems.displayed(), equalTo(expectedItems))
                                        .orComplainWith(MissingTodoItemsException.class,"Missing todos " + expectedItems));
    }

    @Then("{string} should be recorded in his/her/the list")
    public void item_should_be_recorded_in_the_list(String expectedItem) throws Throwable {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), hasItem(expectedItem))
                .orComplainWith(MissingTodoItemsException.class, "Missing todo " + expectedItem));
    }
}
