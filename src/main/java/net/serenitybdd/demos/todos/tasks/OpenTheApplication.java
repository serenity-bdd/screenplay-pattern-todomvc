package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.exceptions.ApplicationNotAvailable;
import net.serenitybdd.demos.todos.pages.ApplicationHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.Matchers.containsString;

public class OpenTheApplication implements Task {

    private ApplicationHomePage applicationHomePage;

    public static OpenTheApplication onTheHomePage() {
        return instrumented(OpenTheApplication.class);
    }

    @Step("{0} opens the application on the home page")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Open.browserOn().the(applicationHomePage));

        theActor.should(seeThat(TheWebPage.title(), containsString("TodoMVC"))
                .orComplainWith(ApplicationNotAvailable.class));
    }
}
