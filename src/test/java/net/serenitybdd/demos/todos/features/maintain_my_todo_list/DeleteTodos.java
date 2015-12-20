package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

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

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;

@RunWith(SerenityRunner.class)
public class DeleteTodos {

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
    public void deleted_items_should_be_removed_from_the_list() {

        givenThat(james).wasAbleTo(OpenTheApplication.onTheHomePage());
        andThat(james).wasAbleTo(AddTodoItems.called("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                DeleteAnItem.called("Walk the dog")
        );

        then(james).should(seeThat(theDisplayedItems, contains("Put out the garbage")));
    }

}
