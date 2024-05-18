package net.serenitybdd.demos.todos.screenplay.maintain_my_todo_list;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.screenplay.questions.CurrentFilter;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.CompleteItem;
import net.serenitybdd.demos.todos.screenplay.tasks.FilterItems;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("Screenplay")
public class FilteringTodoItems {

    private Actor james = Actor.named("James");
    @Managed private WebDriver hisBrowser;
    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_view_only_completed_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Completed)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog")));
        and(james).should(seeThat(TheItems.displayed(), not(contains("Put out the garbage"))));
        and(james).should(seeThat(CurrentFilter.selected(), is(Completed)));
    }

    @Test
    public void should_be_able_to_view_only_incomplete_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
        and(james).should(seeThat(TheItems.displayed(), not(contains("Walk the dog"))));
        and(james).should(seeThat(CurrentFilter.selected(), is(Active)));
    }

    @ParameterizedTest
    @MethodSource("todoTestData")
    @DisplayName("Foo bar")
    public void should_be_able_to_view_various_todo_combinations(String completeItem,
                                                                 String incompleteItem,
                                                                 TodoStatusFilter filterOption,
                                                                 List<String> filteredItems) {
        givenThat(james).wasAbleTo(Start.withATodoListContaining(completeItem, incompleteItem));

        when(james).attemptsTo(
                CompleteItem.called(completeItem),
                FilterItems.toShow(filterOption)
        );

        then(james).should(seeThat(TheItems.displayed(), contains(filteredItems.toArray(new String[]{}))));
        and(james).should(seeThat(CurrentFilter.selected(), is(filterOption)));
    }

    private static Stream<Arguments> todoTestData() {
        return Stream.of(
                Arguments.of("Walk the dog", "Put out the garbage", All, asList("Walk the dog", "Put out the garbage")),
                Arguments.of("Walk the dog", "Put out the garbage", Active, asList("Put out the garbage")),
                Arguments.of("Walk the dog", "Put out the garbage", Completed, asList("Walk the dog"))
                // Add more combinations of items and filter options here
        );
    }
}
