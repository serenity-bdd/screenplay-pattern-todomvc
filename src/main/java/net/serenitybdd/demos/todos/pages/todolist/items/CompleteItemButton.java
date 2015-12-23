package net.serenitybdd.demos.todos.pages.todolist.items;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

class CompleteItemButton extends PageObject {

    private static String COMPLETE_BUTTON = "//*[@class='view' and contains(.,'%s')]//input[@type='checkbox']";

    public static Target forItemCalled(String itemName) {
        return Target.the("Complete button").locatedBy(String.format(COMPLETE_BUTTON, itemName));
    }
}
