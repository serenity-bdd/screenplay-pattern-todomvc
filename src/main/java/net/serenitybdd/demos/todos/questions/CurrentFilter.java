package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.user_interface.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

@Subject("the displayed todo items")
public class CurrentFilter implements Question<TodoStatusFilter> {

    @Override
    public TodoStatusFilter answeredBy(Actor actor) {
        return Text.of(ToDoList.SELECTED_FILTER).viewedBy(actor).asEnum(TodoStatusFilter.class);
    }
}
