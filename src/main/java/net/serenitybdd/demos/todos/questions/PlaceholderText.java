package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.todolist.newitem.NewTodoForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("the ToDo placeholder text")
public class PlaceholderText implements Question<String> {

    private NewTodoForm newTodoForm;

    @Override
    public String answeredBy(Actor actor) {
        return newTodoForm.getPlaceholderText();
    }
}