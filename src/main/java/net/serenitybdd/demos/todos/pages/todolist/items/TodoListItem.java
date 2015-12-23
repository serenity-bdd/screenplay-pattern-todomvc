package net.serenitybdd.demos.todos.pages.todolist.items;

import com.google.common.base.Preconditions;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;

public class TodoListItem extends PageObject {

    private String itemName;

    public TodoListItem forItem(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Boolean isChecked() {
        Preconditions.checkNotNull(itemName, "Select an item using forName() before calling this method");
        return $(completeButton()).isSelected();
    }

    public Target completeButton() {
        Preconditions.checkNotNull(itemName, "Select an item using forName() before calling this method");
        return CompleteItemButton.forItemCalled(itemName);
    }

    public Target deleteButton() {
        Preconditions.checkNotNull(itemName, "Select an item using forName() before calling this method");
        return DeleteItemButton.forItemCalled(itemName);
    }

    public Target text() {
        Preconditions.checkNotNull(itemName, "Select an item using forName() before calling this method");
        return ItemTextLabel.forItemCalled(itemName);
    }

    public Target inlineEditField() {
        Preconditions.checkNotNull(itemName, "Select an item using forName() before calling this method");
        return ItemTextInlineInput.forItemCalled(itemName);
    }
}
