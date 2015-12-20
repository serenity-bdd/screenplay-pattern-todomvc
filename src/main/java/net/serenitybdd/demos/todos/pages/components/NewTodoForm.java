package net.serenitybdd.demos.todos.pages.components;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

public class NewTodoForm extends PageObject {

    public static Target NEW_TODO_FIELD = Target.the("New Todo Field").locatedBy("#new-todo");

    public String getPlaceholderText() {
        return $(NEW_TODO_FIELD).getAttribute("placeholder");
    }
}
