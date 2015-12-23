package net.serenitybdd.demos.todos.pages.components;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;

public class Filter extends PageObject {

    public static Target by(TodoStatusFilter filter) {
        return Target.the(filter + " filter").locatedBy(filterLabelFor(filter));
    }

    private static String filterLabelFor(TodoStatusFilter filter) {
        return String.format("//*[@id='filters']//a[.='%s']", filter);
    }
}
