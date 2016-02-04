package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.user_interface.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

public class CompleteAllItems implements Task {

    @Override
    @Step("Completes all items")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(ToDoList.COMPLETE_ALL));
    }
}
