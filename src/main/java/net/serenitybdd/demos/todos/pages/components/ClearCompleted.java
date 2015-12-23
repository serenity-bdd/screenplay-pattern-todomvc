package net.serenitybdd.demos.todos.pages.components;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

public class ClearCompleted extends PageObject {

    public static final Target BUTTON = Target.the("Clear completed button").locatedBy("#clear-completed");

    public Boolean isVisible() {
        return $(BUTTON).isVisible();
    }

}
