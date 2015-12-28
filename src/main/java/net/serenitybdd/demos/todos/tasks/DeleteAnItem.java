package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.action.JSClick;
import net.serenitybdd.demos.todos.pages.todolist.items.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteAnItem implements Task {

    private final String itemName;

    protected DeleteAnItem(String itemName) {
        this.itemName = itemName;
    }

    @Step("{0} deletes the item '#itemName'")
    public <T extends Actor> void performAs(T theActor) {
        Target deleteButton = TodoListItem.DELETE_ITEM_BUTTON.of(itemName);
        theActor.attemptsTo(JSClick.on(deleteButton));
    }

    public static DeleteAnItem called(String itemName) {
        return instrumented(DeleteAnItem.class, itemName);
    }
}
