package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.todolist.counter.TodoCounter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ItemsLeftCounter implements Question<Integer> {
    @Override
    public Integer answeredBy(Actor actor) {
        return Text.of(TodoCounter.ITEM_COUNT)
                   .viewedBy(actor)
                   .asInteger();
    }
}
