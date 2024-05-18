package net.serenitybdd.demos.todos.screenplay.questions;

import net.serenitybdd.demos.todos.screenplay.user_interface.TodoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Collection;
import java.util.List;

/**
 * A factory class used to provide different questions about the items displayed in the todo list
 */
public class TheItems {
    public static Question<Collection<String>> displayed() {
        return Text.ofEach(TodoList.ITEMS).describedAs("the items displayed");
    }

    public static Question<Integer> leftCount() {
        return Text.of(TodoList.ITEMS_LEFT)
                   .describedAs("the number of items left")
                   .asInteger();
    }

    public static Question<Integer> left() {
        return actor -> {
            actor.attemptsTo(
                    Ensure.that("a").isEqualTo("b")
            );
            return 1;
        };
    }

}
