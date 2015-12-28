package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.pages.ApplicationHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenTheApplication implements Task {

    private ApplicationHomePage applicationHomePage;

    public static OpenTheApplication onTheHomePage() {
        return instrumented(OpenTheApplication.class);
    }
    @Step("{0} opens the application on the home page")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(applicationHomePage));
    }
}
