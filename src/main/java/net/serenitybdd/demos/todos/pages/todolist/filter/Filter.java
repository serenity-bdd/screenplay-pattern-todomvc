package net.serenitybdd.demos.todos.pages.todolist.filter;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;

public class Filter extends PageObject {

    private static final String SELECTED_FILTER = "#filters li .selected";

    public static Target by(TodoStatusFilter filter) {
        return Target.the(filter + " filter").locatedBy(filterLabelFor(filter));
    }

    public TodoStatusFilter getSelected() {
        return TodoStatusFilter.valueOf($(SELECTED_FILTER).getText());
    }

    private static String filterLabelFor(TodoStatusFilter filter) {
        return String.format("//*[@id='filters']//a[.='%s']", filter);
    }
}
