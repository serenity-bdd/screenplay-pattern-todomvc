package net.serenitybdd.demos.todos.jbehave.steps;

import net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.AddATodoItem;
import net.serenitybdd.demos.todos.screenplay.tasks.CompleteItem;
import net.serenitybdd.demos.todos.screenplay.tasks.FilterItems;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.jbehave.core.annotations.*;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class TodoUserSteps {

    @BeforeScenario
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("that $actorName has an empty todo list")
    public void that_James_has_an_empty_todo_list(String actorName) throws Throwable {
        theActorCalled(actorName).wasAbleTo(Start.withAnEmptyTodoList());
    }

    @Given("that $actorName has a todo list containing $items")
    public void that_James_has_an_empty_todo_list(String actorName, List<String> items) throws Throwable {
        theActorCalled(actorName).wasAbleTo(Start.withATodoListContaining(items));
    }

    @When("he adds '$item' to the list")
    @Alias("she adds '$item' to the list")
    public void adds_Buy_some_milk_to_his_list(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(AddATodoItem.called(item));
    }

    @Then("the todo list should contain $items")
    public void todo_list_should_contain(List<String> expectedItems) throws Throwable {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(expectedItems)));
    }

    @When("he has completed the task called '$item'")
    @Alias("she has completed the task called '$item'")
    public void completes_task_called(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(
                CompleteItem.called(item)
        );
    }

    @Given("he has completed the task called '$item'")
    @Alias("she has completed the task called '$item'")
    public void given_completes_task_called(String item) throws Throwable {
        theActorInTheSpotlight().attemptsTo(
                CompleteItem.called(item)
        );
    }


    @When("he filters her list to show only $status tasks")
    @Alias("she filters her list to show only $status tasks")
    public void filters_tasks_by(TodoStatusFilter status) {
        theActorInTheSpotlight().attemptsTo(FilterItems.toShow(status));
    }


    @Then("$actorName's todo list should contain $expectedItems")
    public void a_users_todo_list_should_contain(String actorName, List<String> expectedItems) throws Throwable {
        theActorCalled(actorName).should(seeThat(TheItems.displayed(), equalTo(expectedItems)));
    }

    @Then("'$expectedItem' should be recorded in the list")
    public void item_should_be_recorded_in_the_list(String expectedItem) throws Throwable {
       theActorInTheSpotlight().should(seeThat(TheItems.displayed(), hasItem(expectedItem)));
    }
}
