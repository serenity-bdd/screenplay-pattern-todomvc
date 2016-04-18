package net.serenitybdd.demos.todos.screenplay.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.screenplay.questions.ClearCompletedItems;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.questions.ElementAvailability;
import net.serenitybdd.demos.todos.screenplay.tasks.Clear;
import net.serenitybdd.demos.todos.screenplay.tasks.CompleteItem;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

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
    public void should_be_able_to_clear_completed_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                Clear.completedItems()
        );

        then(james).should(GivenWhenThen.seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    public void should_not_be_able_to_clear_completed_todos_if_none_are_complete() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        then(james).should(seeThat(ClearCompletedItems.option(), Matchers.is(ElementAvailability.Unavailable)));
    }
}