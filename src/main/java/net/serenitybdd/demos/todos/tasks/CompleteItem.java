package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.demos.todos.user_interface.TodoListItem.COMPLETE_ITEM;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompleteItem implements Task {

    private final String itemName;

    @Override
    @Step("{0} completes the item called #itemName")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(COMPLETE_ITEM.of(itemName)));
    }

    public static CompleteItem called(String itemName) {
        return instrumented(CompleteItem.class, itemName);
    }
    public CompleteItem(String itemName) { this.itemName = itemName; }
}