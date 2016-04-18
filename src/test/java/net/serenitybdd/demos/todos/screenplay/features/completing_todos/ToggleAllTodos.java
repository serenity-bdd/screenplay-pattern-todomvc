package net.serenitybdd.demos.todos.screenplay.features.completing_todos;

import net.serenitybdd.demos.todos.screenplay.questions.TheItemStatus;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.demos.todos.screenplay.tasks.ToggleStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.demos.todos.screenplay.model.TodoStatus.Active;
import static net.serenitybdd.demos.todos.screenplay.model.TodoStatus.Completed;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SerenityRunner.class)
@WithTag("Screenplay pattern")
public class ToggleAllTodos {

    @Managed private WebDriver hisBrowser;
    private Actor james = Actor.named("James");
    @Before public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_quickly_complete_all_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            ToggleStatus.ofAllItems()
        );

        then(james).should(
            seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Completed)),
            seeThat(TheItemStatus.forTheItemCalled("Put out the garbage"), is(Completed))
        );
    }

    @Test
    public void should_be_able_to_toggle_status_of_all_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            ToggleStatus.ofAllItems(),
            ToggleStatus.ofAllItems()
        );

        then(james).should(
            seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Active)),
            seeThat(TheItemStatus.forTheItemCalled("Put out the garbage"), is(Active))
        );
    }


    @Test
    public void should_see_that_there_are_zero_items_todo_when_all_are_toggled_complete() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            ToggleStatus.ofAllItems()
        );

        then(james).should(
            seeThat(TheItems.leftCount(), is(0))
        );
    }

    @Test
    public void should_see_how_many_items_todo_when_all_are_toggled_to_incomplete() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            ToggleStatus.ofAllItems(),
            ToggleStatus.ofAllItems()
        );

        then(james).should(
            seeThat(TheItems.leftCount(), is(2))
        );
    }
}