package net.serenitybdd.demos.todos.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;

import static net.serenitybdd.demos.todos.cucumber.actors.OnStage.theActorCalled;

public class TodoUserSteps {

    @Given("^that (.*) has an empty todo list$")
    public void that_James_has_an_empty_todo_list(String actorName) throws Throwable {
        theActorCalled(actorName).wasAbleTo(Start.withAnEmptyTodoList());
    }

    @When("^he adds 'Buy some milk' to his list$")
    public void he_adds_Buy_some_milk_to_his_list() throws Throwable {
//        when(james).attemptsTo(AddATodoItem.called("Buy some milk"));
    }

    @Then("^'Buy some milk' should be recorded in his list$")
    public void buy_some_milk_should_be_recorded_in_his_list() throws Throwable {
//        then(james).should(seeThat(TheItems.displayed(), hasItem("Buy some milk")));
    }
}
