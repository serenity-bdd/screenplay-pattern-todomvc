package net.serenitybdd.demos.todos.screenplay.questions;

import net.serenitybdd.screenplay.Question;

import java.util.List;

/**
 * A factory class used to provide different questions about the items displayed in the todo list
 */
public class TheItems {
    public static Question<List<String>> displayed() {
        return new DisplayedItems();
    }

    public static Question<Integer> leftCount() {
        return new ItemsLeftCounter();
    }
}
