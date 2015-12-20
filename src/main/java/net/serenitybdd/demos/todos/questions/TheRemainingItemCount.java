package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.components.TodoCounter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class TheRemainingItemCount implements Question<Integer> {
    public static TheRemainingItemCount value() {
        return new TheRemainingItemCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        TodoCounter todoCounter = BrowseTheWeb.as(actor).on(TodoCounter.class);
        return todoCounter.getValue();
    }
}
