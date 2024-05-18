package net.serenitybdd.demos.todos.screenplay.maintain_my_todo_list;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.DeleteAnItem;
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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("Screenplay")
public class DeletingTodoItems {

    @Managed
    private WebDriver hisBrowser;

    private Actor james = Actor.named("James");

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_delete_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(DeleteAnItem.called("Walk the dog"));

        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    public void should_see_deleting_a_todo_decreases_the_remaining_items_count() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                DeleteAnItem.called("Walk the dog")
        );

        then(james).should(seeThat(TheItems.leftCount(), is(1)));
    }
}
