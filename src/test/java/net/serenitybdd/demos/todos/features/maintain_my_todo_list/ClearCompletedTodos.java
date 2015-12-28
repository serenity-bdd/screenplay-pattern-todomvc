package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.questions.ClearCompletedItemsOptionAvailability;
import net.serenitybdd.demos.todos.questions.DisplayedItems;
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

import static net.serenitybdd.demos.todos.questions.ElementAvailability.Unavailable;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class ClearCompletedTodos {

    @Managed
    private
    WebDriver hisBrowser;

    @Managed
    private
    WebDriver herBrowser;

    private Actor james = Actor.named("James");
    private Actor jane = Actor.named("Jane");

    @Steps
    private
    ClearCompletedItems clearTheCompletedItems;

    private DisplayedItems theDisplayedItems = new DisplayedItems();

    private ClearCompletedItemsOptionAvailability theClearCompletedItemsOption = new ClearCompletedItemsOptionAvailability();

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
                Complete.itemCalled("Walk the dog"),
                clearTheCompletedItems);

        then(james).should(seeThat(theDisplayedItems, contains("Put out the garbage")));
    }

    @Test
    public void cleared_completed_option_should_not_be_available_if_no_items_are_completed() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        then(james).should(seeThat(theClearCompletedItemsOption, equalTo(Unavailable)));
    }

    @Test
    public void clearing_completed_items_should_not_affect_items_belonging_to_other_users() {
        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(jane).wasAbleTo(OpenTheApplication.onTheHomePage());

        givenThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));
        andThat(jane).wasAbleTo(AddTodoItems.called("Walk the dog", "Feed the cat"));

        when(james).attemptsTo(
                Complete.itemCalled("Walk the dog"),
                clearTheCompletedItems);

        then(jane).should(seeThat(theDisplayedItems, contains("Walk the dog", "Feed the cat")));
    }

}
