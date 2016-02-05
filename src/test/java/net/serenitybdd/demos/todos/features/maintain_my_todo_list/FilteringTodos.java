package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.questions.CurrentFilter;
import net.serenitybdd.demos.todos.questions.TheItems;
import net.serenitybdd.demos.todos.tasks.CompleteItem;
import net.serenitybdd.demos.todos.tasks.FilterItems;
import net.serenitybdd.demos.todos.tasks.Start;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.demos.todos.model.TodoStatusFilter.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class FilteringTodos {

    private Actor james = Actor.named("James");
    @Managed private WebDriver hisBrowser;
    @Before public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void filtering_by_completed() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Completed)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog")));
    }

    @Test
    public void filtering_by_active() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    public void filtering_by_all() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active),
            FilterItems.toShow(All)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog", "Put out the garbage")));
    }

    @Test
    public void should_indicate_what_filter_is_currently_being_used() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        then(james).should(seeThat(CurrentFilter.selected(), is(All)));

        when(james).attemptsTo(FilterItems.toShow(Active));
        then(james).should(seeThat(CurrentFilter.selected(), is(Active)));
    }
}