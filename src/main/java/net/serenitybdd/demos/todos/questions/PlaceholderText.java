package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.todolist.newitem.NewTodoForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Attribute;

@Subject("the ToDo placeholder text")
public class PlaceholderText implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Attribute.of(NewTodoForm.NEW_TODO_FIELD).named("placeholder").viewedBy(actor).asString();
    }
}