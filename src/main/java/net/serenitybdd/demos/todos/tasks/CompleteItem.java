package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.pages.components.ToDoList;
import net.serenitybdd.demos.todos.pages.components.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

public class CompleteItem implements Performable {

    private final String itemName;

    protected CompleteItem(String itemName) {
        this.itemName = itemName;
    }

    TodoListItem listItem;

    @Override
    @Step("Completes item called #itemName")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(listItem.forItem(itemName).completeButton()));
    }
}
