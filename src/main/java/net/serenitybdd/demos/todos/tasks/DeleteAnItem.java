package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.action.SendClick;
import net.serenitybdd.demos.todos.pages.components.DeleteItemButton;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteAnItem implements Performable {

    private final String itemName;

    protected DeleteAnItem(String itemName) {
        this.itemName = itemName;
    }

    @Step("{0} deletes the item '#itemName'")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(SendClick.to(DeleteItemButton.forItemCalled(itemName)));
    }

    public static DeleteAnItem called(String itemName) {
        return instrumented(DeleteAnItem.class, itemName);
    }
}
