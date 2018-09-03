package net.serenitybdd.demos.todos.screenplay.tasks;

import net.serenitybdd.demos.todos.screenplay.user_interface.TodoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ToggleStatus implements Task {

    @Override
    @Step("{0} toggles the status of all items")
    public <T extends Actor> void performAs(T theActor) {
        BrowseTheWeb.as(theActor).evaluateJavascript("arguments[0].click();",
                                                     TodoList.TOGGLE_ALL.resolveFor(theActor));
    }

    public static ToggleStatus ofAllItems() {
        return instrumented(ToggleStatus.class);
    }
}