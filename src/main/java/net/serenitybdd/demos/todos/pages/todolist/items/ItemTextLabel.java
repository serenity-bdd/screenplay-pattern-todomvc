package net.serenitybdd.demos.todos.pages.todolist.items;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

class ItemTextLabel extends PageObject {

    private static String ITEM_TEXT_LABEL = "//*[@class='view' and contains(.,'%s')]//label";

    public static Target forItemCalled(String itemName) {
        return Target.the("Item text label").locatedBy(String.format(ITEM_TEXT_LABEL, itemName));
    }
}
