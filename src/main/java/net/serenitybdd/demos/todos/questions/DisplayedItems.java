package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.pages.todolist.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

@Subject("the displayed todo items")
public class DisplayedItems implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        return Text.of(ToDoList.TODO_ITEMS).viewedBy(actor).asList();
    }
}
