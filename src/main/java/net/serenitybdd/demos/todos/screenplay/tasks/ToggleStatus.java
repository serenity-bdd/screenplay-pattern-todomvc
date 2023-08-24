package net.serenitybdd.demos.todos.screenplay.tasks;

import net.serenitybdd.annotations.WithDriver;
import net.serenitybdd.demos.todos.screenplay.user_interface.TodoList;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Evaluate;
import org.openqa.selenium.WebElement;

public class ToggleStatus {
    public static Performable ofAllItems() {
        return Task.where("{0} toggles the status of all items",
                actor -> {
                    WebElement toggleAll = TodoList.TOGGLE_ALL_BUTTON.resolveFor(actor);
                    actor.attemptsTo(Evaluate.javascript("arguments[0].click();", toggleAll));
                }
        );
    }
}
