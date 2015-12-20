package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.components.NewTodoForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("the ToDo placeholder text")
public class PlaceholderText implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).on(NewTodoForm.class).getPlaceholderText();
    }
}
