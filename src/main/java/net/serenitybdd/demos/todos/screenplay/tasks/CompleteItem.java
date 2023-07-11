package net.serenitybdd.demos.todos.screenplay.tasks;

import net.serenitybdd.demos.todos.screenplay.user_interface.TodoListItem;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class CompleteItem {
    public static Performable called(String item) {
        return Task.where("{0} completes the item called" + item,
                Click.on(TodoListItem.COMPLETE_ITEM.of(item))
        );
    }
}
