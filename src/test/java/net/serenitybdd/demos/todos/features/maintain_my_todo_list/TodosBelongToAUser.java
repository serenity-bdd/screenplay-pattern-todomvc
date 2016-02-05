package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

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

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;

@RunWith(SerenityRunner.class)
public class TodosBelongToAUser {

    private Actor james = Actor.named("James");
    private Actor jane = Actor.named("Jane");

    @Managed private WebDriver hisBrowser;
    @Managed private WebDriver herBrowser;

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
        jane.can(BrowseTheWeb.with(herBrowser));
    }

    @Test
    public void my_todo_list_is_unaffected_by_others() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        andThat(jane).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Feed the cat"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                Clear.completedItems()
        );

        then(jane).should(seeThat(TheItems.displayed(), contains("Walk the dog", "Feed the cat")));
    }
}