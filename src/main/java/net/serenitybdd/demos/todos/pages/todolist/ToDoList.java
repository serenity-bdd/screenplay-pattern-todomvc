package net.serenitybdd.demos.todos.pages.todolist;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

public class ToDoList extends PageObject {
    public static Target TODO_ITEMS = Target.the("Todo items").locatedBy(".view label");
}
