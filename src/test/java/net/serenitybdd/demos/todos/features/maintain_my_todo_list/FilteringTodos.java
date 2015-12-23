package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.questions.CurrentFilter;
import net.serenitybdd.demos.todos.questions.DisplayedItems;
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

import static net.serenitybdd.demos.todos.model.TodoStatusFilter.Active;
import static net.serenitybdd.demos.todos.model.TodoStatusFilter.All;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class FilteringTodos {

    private Actor james = Actor.named("James");
    @Managed
    private WebDriver hisBrowser;

    @Steps
    private
    DisplayedItems theDisplayedItems;

    private CurrentFilter theCurrentFilter = new CurrentFilter();

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void filtering_by_completed() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.itemCalled("Walk the dog"),
                FilterItems.byStatus(TodoStatusFilter.Completed));

        then(james).should(seeThat(theDisplayedItems, contains("Walk the dog")));
    }

    @Test
    public void filtering_by_active() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.itemCalled("Walk the dog"),
                FilterItems.byStatus(Active));

        then(james).should(seeThat(theDisplayedItems, contains("Put out the garbage")));
    }

    @Test
    public void filtering_by_all() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                Complete.itemCalled("Walk the dog"),
                FilterItems.byStatus(Active),
                FilterItems.byStatus(All));

        then(james).should(seeThat(theDisplayedItems, contains("Walk the dog", "Put out the garbage")));
    }

    @Test
    public void should_indicate_what_filter_is_currently_being_used() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());

        when(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));
        then(james).should(seeThat(theCurrentFilter, is(All)));

        when(james).attemptsTo(FilterItems.byStatus(Active));
        then(james).should(seeThat(theCurrentFilter, is(Active)));
    }

}
