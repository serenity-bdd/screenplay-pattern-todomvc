package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.pages.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

public class ClearCompletedItems implements Task {

    @Step("{0} clears all the completed items")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(ToDoList.CLEAR_COMPLETED));
    }
}
