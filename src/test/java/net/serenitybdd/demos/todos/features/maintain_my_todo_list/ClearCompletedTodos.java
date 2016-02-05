package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.questions.ClearCompletedItems;
import net.serenitybdd.demos.todos.questions.TheItems;
import net.serenitybdd.demos.todos.tasks.Clear;
import net.serenitybdd.demos.todos.tasks.CompleteItem;
import net.serenitybdd.demos.todos.tasks.Start;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.demos.todos.questions.ElementAvailability.Unavailable;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class ClearCompletedTodos {

    @Managed private WebDriver hisBrowser;
    private Actor james = Actor.named("James");
    @Before public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void cleared_completed_items_should_disappear_from_the_todo_list() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                Clear.completedItems()
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    public void cleared_completed_option_should_not_be_available_if_no_items_are_completed() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        then(james).should(seeThat(ClearCompletedItems.option(), is(Unavailable)));
    }
}