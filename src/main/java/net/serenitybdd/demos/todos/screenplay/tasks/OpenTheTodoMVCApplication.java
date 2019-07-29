package net.serenitybdd.demos.todos.screenplay.tasks;

import cucumber.api.java.Before;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.demos.todos.screenplay.user_interface.ApplicationHomePage;
import net.serenitybdd.demos.todos.screenplay.user_interface.TodoMVCHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;


public class OpenTheTodoMVCApplication implements Performable {

    private TodoMVCHomePage todoMVCHomePage;

    public static Performable onTheHomePage() {
        return Instrumented.instanceOf(OpenTheTodoMVCApplication.class).newInstance();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(todoMVCHomePage),
                Click.on(By.linkText("AngularJS"))
        );
    }
}
