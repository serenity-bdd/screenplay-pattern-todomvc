package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.pages.todolist.CompleteAll;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

public class CompleteAllItems implements Performable {

    @Override
    @Step("Completes item called #itemName")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(CompleteAll.BUTTON));
    }
}
