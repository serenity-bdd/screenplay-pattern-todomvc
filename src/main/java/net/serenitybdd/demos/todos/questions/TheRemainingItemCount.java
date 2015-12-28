package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.todolist.counter.TodoCounter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheRemainingItemCount implements Question<Integer> {
    public static TheRemainingItemCount value() {
        return new TheRemainingItemCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return Text.of(TodoCounter.ITEM_COUNT)
                   .onTheScreenOf(actor)
                   .asInteger();
    }
}
