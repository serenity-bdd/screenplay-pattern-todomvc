package net.serenitybdd.demos.todos.pages.components;

import com.google.common.base.Preconditions;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

public class TodoListItem extends PageObject {

    private static String COMPLETE_BUTTON = "//*[@class='view' and contains(.,'%s')]//input[@type='checkbox']";

    private String itemName;

    public TodoListItem forItem(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Boolean isChecked() {
        Preconditions.checkNotNull(itemName, "Select an item using forName() before calling this method");
        return $(completeButtonFor(itemName)).isSelected();
    }

    public Target completeButton() {
        return completeButtonFor(itemName);
    }

    private static Target completeButtonFor(String itemName) {
        return Target.the("Complete button").locatedBy(String.format(COMPLETE_BUTTON, itemName));
    }
}
