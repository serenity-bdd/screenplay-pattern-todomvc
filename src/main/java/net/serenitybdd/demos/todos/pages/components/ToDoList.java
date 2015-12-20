package net.serenitybdd.demos.todos.pages.components;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

public class ToDoList extends PageObject {
    public static Target TODO_ITEMS = Target.the("Todo items").locatedBy(".view label");

    public static Target deleteButtonForItem(String itemName) {
        String DELETE_BUTTON = "//*[@class='view' and contains(.,'%s')]//button[@class='destroy']";
        return Target.the("Delete button").locatedBy(String.format(DELETE_BUTTON, itemName));
    }

    public static Target completeButtonFor(String itemName) {
        String COMPLETE_BUTTON = "//*[@class='view' and contains(.,'%s')]//input[@type='checkbox']";
        return Target.the("Complete button").locatedBy(String.format(COMPLETE_BUTTON, itemName));
    }

    public Boolean isChecked(String itemName) {
        return $(completeButtonFor(itemName)).isSelected();
    }

}
