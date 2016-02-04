package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.pages.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

public class CompleteItem implements Task {

    private final String itemName;

    protected CompleteItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    @Step("{0} completes the item called #itemName")
    public <T extends Actor> void performAs(T theActor) {
        Target completeButton = TodoListItem.COMPLETE_ITEM.of(itemName);
        theActor.attemptsTo(Click.on(completeButton));
    }
}
