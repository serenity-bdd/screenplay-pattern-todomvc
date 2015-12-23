package net.serenitybdd.demos.todos.pages.todolist.items;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

class DeleteItemButton extends PageObject {

    private static String DELETE_BUTTON = "//*[@class='view' and contains(.,'%s')]//button[@class='destroy']";

    public static Target forItemCalled(String itemName) {
        return Target.the("Delete button").locatedBy(String.format(DELETE_BUTTON, itemName));
    }
}
