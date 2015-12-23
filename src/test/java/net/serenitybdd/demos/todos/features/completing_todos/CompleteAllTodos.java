package net.serenitybdd.demos.todos.features.completing_todos;

import net.serenitybdd.demos.todos.questions.DisplayedItems;
import net.serenitybdd.demos.todos.questions.TheItemStatus;
import net.serenitybdd.demos.todos.questions.TheRemainingItemCount;
import net.serenitybdd.demos.todos.tasks.AddTodoItems;
import net.serenitybdd.demos.todos.tasks.ClearCompletedItems;
import net.serenitybdd.demos.todos.tasks.Complete;
import net.serenitybdd.demos.todos.tasks.OpenTheApplication;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.demos.todos.model.TodoStatus.Active;
import static net.serenitybdd.demos.todos.model.TodoStatus.Completed;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SerenityRunner.class)
public class CompleteAllTodos {

    @Managed
    private
    WebDriver hisBrowser;

    private Actor james = Actor.named("James");

    @Steps
    ClearCompletedItems clearTheCompletedItems;

    @Steps
    private
    DisplayedItems theDisplayedItems;

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }


    @Test
    public void should_be_able_to_complete_all_todos_with_a_single_action() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.allItems()
        );

        then(james).should(
                seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Completed)),
                seeThat(TheItemStatus.forTheItemCalled("Put out the garbage"), is(Completed))
        );
    }


    @Test
    public void complete_todos_can_be_toggled() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.allItems(),
                Complete.allItems()
        );

        then(james).should(
                seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Active)),
                seeThat(TheItemStatus.forTheItemCalled("Put out the garbage"), is(Active))
        );
    }


    @Test
    public void complete_all_todos_should_set_the_remaining_count_to_zero() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.allItems()
        );

        then(james).should(
                seeThat(TheRemainingItemCount.value(), is(0))
        );
    }

    @Test
    public void when_complete_all_is_toggled_the_remaining_counter_should_be_restored() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.allItems(),
                Complete.allItems()
        );

        then(james).should(
                seeThat(TheRemainingItemCount.value(), is(2))
        );
    }

}
