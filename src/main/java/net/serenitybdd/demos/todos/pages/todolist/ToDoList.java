package net.serenitybdd.demos.todos.pages.todolist;

import net.serenitybdd.screenplay.targets.Target;

public class ToDoList {
    public static Target TODO_ITEMS = Target.the("Todo items").locatedBy(".view label");
}
