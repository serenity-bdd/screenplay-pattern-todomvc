package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.todolist.counter.TodoCounter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheRemainingItemCount implements Question<Integer> {
    public static TheRemainingItemCount value() {
        return new TheRemainingItemCount();
    }

    private TodoCounter todoCounter;

    @Override
    public Integer answeredBy(Actor actor) {
        return todoCounter.getValue();
    }
}
