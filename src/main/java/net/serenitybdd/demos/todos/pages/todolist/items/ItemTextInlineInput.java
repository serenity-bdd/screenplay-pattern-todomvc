package net.serenitybdd.demos.todos.pages.todolist.items;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

class ItemTextInlineInput extends PageObject {

    private static String ITEM_TEXT_INPUT = "//li[contains(.,'%s')]//form/input";

    public static Target forItemCalled(String itemName) {
        return Target.the("Item text label").locatedBy(String.format(ITEM_TEXT_INPUT, itemName));
    }
}
