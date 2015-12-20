package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.questions.DisplayedItems;
import net.serenitybdd.demos.todos.tasks.AddTodoItems;
import net.serenitybdd.demos.todos.tasks.ClearCompletedItems;
import net.serenitybdd.demos.todos.tasks.CompleteItem;
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
public class ClearCompletedTodos {

    @Managed
    WebDriver hisBrowser;

    @Managed
    WebDriver herBrowser;

    Actor james = Actor.named("James");
    Actor jane = Actor.named("Jane");

    @Steps
    ClearCompletedItems clearTheCompletedItems;

    @Steps
    DisplayedItems theDisplayedItems;

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
        jane.can(BrowseTheWeb.with(herBrowser));
    }

    @Test
    public void cleared_completed_items_should_disappear_from_the_todo_list() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                clearTheCompletedItems);

        then(james).should(seeThat(theDisplayedItems, contains("Put out the garbage")));
    }

    @Test
    public void clearing_completed_items_should_not_affect_items_belonging_to_other_users() {
        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(jane).wasAbleTo(OpenTheApplication.onTheHomePage());

        givenThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));
        andThat(jane).wasAbleTo(AddTodoItems.called("Walk the dog", "Feed the cat"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                clearTheCompletedItems);

        then(jane).should(seeThat(theDisplayedItems, contains("Walk the dog", "Feed the cat")));
    }


}
