package net.serenitybdd.demos.todos.pages.todolist.filter;

import net.serenitybdd.screenplay.targets.Target;

public class FilterSelection {
    public static Target SELECTED_FILTER = Target.the("selected filter").locatedBy("#filters li .selected");
    public static Target FILTER = Target.the("filter").locatedBy("//*[@id='filters']//a[.='{0}']");
}
