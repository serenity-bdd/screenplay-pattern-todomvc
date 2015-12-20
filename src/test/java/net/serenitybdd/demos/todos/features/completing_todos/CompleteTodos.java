package net.serenitybdd.demos.todos.features.completing_todos;

import net.serenitybdd.demos.todos.questions.DisplayedItems;
import net.serenitybdd.demos.todos.questions.TheItemStatus;
import net.serenitybdd.demos.todos.questions.TheRemainingItemCount;
import net.serenitybdd.demos.todos.tasks.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.demos.todos.model.TodoStatus.Completed;
import static net.serenitybdd.demos.todos.model.TodoStatusFilter.Active;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

@RunWith(SerenityRunner.class)
public class CompleteTodos {

    @Managed
    WebDriver hisBrowser;

    Actor james = Actor.named("James");

    @Steps
    ClearCompletedItems clearTheCompletedItems;

    @Steps
    DisplayedItems theDisplayedItems;

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void completed_items_should_be_marked_as_completed_in_the_main_list() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog")
        );

        then(james).should(
                seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Completed)),
                seeThat(TheRemainingItemCount.value(), is(1)));
    }

    @Test
    public void items_left_counter_should_be_decremented_when_an_item_is_completed() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog")
        );

        then(james).should(seeThat(TheRemainingItemCount.value(), is(1)));
    }


    @Test
    public void completed_items_should_not_appear_in_the_active_list() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                FilterItems.byStatus(Active)
        );

        then(james).should(seeThat(theDisplayedItems, not(contains("Walk the dog"))));
    }

}
