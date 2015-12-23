package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pages.todolist.filter.Filter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("the displayed todo items")
public class CurrentFilter implements Question<TodoStatusFilter> {

    private Filter filter;

    @Override
    public TodoStatusFilter answeredBy(Actor actor) {
        return filter.getSelected();
    }
}
