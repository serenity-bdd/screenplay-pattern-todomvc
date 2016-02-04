package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.demos.todos.user_interface.ToDoList.COMPLETE_ALL;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompleteAll implements Task {

    @Override
    @Step("Completes all items")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(COMPLETE_ALL));
    }

    public static CompleteAll items() {
        return instrumented(CompleteAll.class);
    }
}