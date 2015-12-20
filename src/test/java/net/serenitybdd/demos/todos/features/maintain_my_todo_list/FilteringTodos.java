package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.questions.DisplayedItems;
import net.serenitybdd.demos.todos.tasks.AddTodoItems;
import net.serenitybdd.demos.todos.tasks.CompleteItem;
import net.serenitybdd.demos.todos.tasks.FilterItems;
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

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;

@RunWith(SerenityRunner.class)
public class FilteringTodos {

    @Managed
    WebDriver hisBrowser;

    Actor james = Actor.named("James");

    @Steps
    DisplayedItems theDisplayedItems;

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void filtering_by_completed() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                FilterItems.byStatus(TodoStatusFilter.Completed));

        then(james).should(seeThat(theDisplayedItems, contains("Walk the dog")));
    }

    @Test
    public void filtering_by_active() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                FilterItems.byStatus(TodoStatusFilter.Active));

        then(james).should(seeThat(theDisplayedItems, contains("Put out the garbage")));
    }

    @Test
    public void filtering_by_all() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                FilterItems.byStatus(TodoStatusFilter.Active),
                FilterItems.byStatus(TodoStatusFilter.All));

        then(james).should(seeThat(theDisplayedItems, contains("Walk the dog", "Put out the garbage")));
    }

}
