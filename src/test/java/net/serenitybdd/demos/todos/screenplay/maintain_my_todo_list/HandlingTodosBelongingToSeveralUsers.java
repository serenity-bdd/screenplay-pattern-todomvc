package net.serenitybdd.demos.todos.screenplay.maintain_my_todo_list;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.Clear;
import net.serenitybdd.demos.todos.screenplay.tasks.CompleteItem;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("Screenplay")
public class HandlingTodosBelongingToSeveralUsers {

    private Actor james = Actor.named("James");
    private Actor jane = Actor.named("Jane");

    @Managed private WebDriver hisBrowser;
    @Managed private WebDriver herBrowser;

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
        jane.can(BrowseTheWeb.with(herBrowser));
    }

    @Test
    public void should_not_affect_todos_belonging_to_another_user() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        andThat(jane).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Feed the cat"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                Clear.completedItems()
        );

        then(jane).should(seeThat(TheItems.displayed(), contains("Walk the dog", "Feed the cat")));
    }
}
