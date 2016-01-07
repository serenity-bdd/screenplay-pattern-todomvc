package net.serenitybdd.demos.todos.features.record_todos;

import net.serenitybdd.demos.todos.questions.TheItems;
import net.serenitybdd.demos.todos.tasks.AddATodoItem;
import net.serenitybdd.demos.todos.tasks.AddTodoItems;
import net.serenitybdd.demos.todos.tasks.OpenTheApplication;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

/**
 * This example illustrates using Serenity Steps with JUnit.
 */
@RunWith(SerenityRunner.class)
public class AddNewTodos {

    private Actor james = Actor.named("James");

    @Managed
    private WebDriver hisBrowser;

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_add_a_todo_item() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());

        when(james).attemptsTo(AddATodoItem.called("Buy some milk"));

        then(james).should(seeThat(TheItems.displayed(), hasItem("Buy some milk")));
    }

    @Test
    public void should_be_able_to_add_a_todo_item_to_an_existing_list() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(AddATodoItem.called("Buy some milk"));

        then(james).should(seeThat(TheItems.displayed(),
                                   hasItems("Walk the dog", "Put out the garbage", "Buy some milk")));
    }

}
