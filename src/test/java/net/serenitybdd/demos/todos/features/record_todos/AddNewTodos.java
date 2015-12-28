package net.serenitybdd.demos.todos.features.record_todos;

import net.serenitybdd.demos.todos.questions.ApplicationDetails;
import net.serenitybdd.demos.todos.questions.DisplayedItems;
import net.serenitybdd.demos.todos.questions.PlaceholderText;
import net.serenitybdd.demos.todos.tasks.AddATodoItem;
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
import static org.hamcrest.Matchers.hasItem;

/**
 * This example illustrates using Serenity Steps with JUnit.
 */
@RunWith(SerenityRunner.class)
public class AddNewTodos {

    private Actor james = Actor.named("James");

    @Managed
    private WebDriver hisBrowser;

    private DisplayedItems theDisplayedItems = new DisplayedItems();

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_add_a_todo_item() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());

        when(james).attemptsTo(AddATodoItem.called("Buy some milk"));

        then(james).should(seeThat(theDisplayedItems, hasItem("Buy some milk")));
    }

}
