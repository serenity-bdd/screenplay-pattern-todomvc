package net.serenitybdd.demos.todos.pages.components;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;

public class SelectedFilter extends PageObject {
    private static final String SELECTED_FILTER = "#filters li .selected";

    public TodoStatusFilter getCurrent() {
        return TodoStatusFilter.valueOf($(SELECTED_FILTER).getText());
    }
}
