package net.serenitybdd.demos.todos.cucumber.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.demos.todos.cucumber.MissingTodoItemsException;
import net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.*;
import net.serenitybdd.demos.todos.screenplay.user_interface.ApplicationHomePage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Collections.EMPTY_LIST;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class TodoUserSteps {

    @Given("^that (.*) has an empty todo list$")
    public void that_James_has_an_empty_todo_list(String actorName) throws Throwable {
        theActorCalled(actorName).wasAbleTo(Start.withAnEmptyTodoList());
    }

    @Given("^that (.*) has a todo list containing (.*)$")
    public void that_James_has_an_empty_todo_list(String actorName, String itemList) throws Throwable {
        List<String> items = split(itemList);
        theActorCalled(actorName).wasAbleTo(Start.withATodoListContaining(items));
    }

    @When("^s?he adds '(.*)' to (?:his|her|the) list$")
    public void adds_Buy_some_milk_to_his_list(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(AddATodoItem.called(item));
    }

    @Then("^(?:his|her|the) todo list should contain (.*)$")
    public void todo_list_should_contain(String itemList) throws Throwable {
        List<String> expectedItems = split(itemList);
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(expectedItems))
                .orComplainWith(MissingTodoItemsException.class,"Missing todos " + expectedItems));
    }

    @Then("^(?:his|her|the) todo list should be empty$")
    public void todo_list_should_be_empty() throws Throwable {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(EMPTY_LIST)));
    }

    @Then("^s?he (?:completes|has completed) the task called '(.*)'$")
    public void completes_task_called(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(
                CompleteItem.called(item)
        );
    }

    @When("s?he filters her list to show only (.*) tasks")
    public void filters_tasks_by(TodoStatusFilter status) {
        theActorInTheSpotlight().attemptsTo(FilterItems.toShow(status));
    }

    @Then("^(.*)'s todo list should contain (.*)$")
    public void a_users_todo_list_should_contain(String actorName, String itemList) throws Throwable {
        List<String> expectedItems = split(itemList);
        theActorCalled(actorName).should(seeThat(TheItems.displayed(), equalTo(expectedItems))
                                        .orComplainWith(MissingTodoItemsException.class,"Missing todos " + expectedItems));
    }

    @Then("^'(.*)' should be recorded in (?:his|her|the) list$")
    public void item_should_be_recorded_in_the_list(String expectedItem) throws Throwable {
       theActorInTheSpotlight().should(seeThat(TheItems.displayed(), hasItem(expectedItem))
                                        .orComplainWith(MissingTodoItemsException.class,"Missing todo " + expectedItem));
    }

    private List<String> split(String itemList) {
        return stream(itemList.split(",")).map(String::trim).collect(Collectors.toList());
    }

    private ApplicationHomePage applicationHomePage;

    @Before(value="@web", order=1)
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Before(order=2)
    public void openApplication() {
        OnStage.aNewActor().attemptsTo(
                OpenTheTodoMVCApplication.onTheHomePage()
        );
    }

    @After
    public void tidyUp() {
        theActorInTheSpotlight().attemptsTo(DeleteAll.items());
    }

}
