package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.user_interface.ApplicationHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Start implements Task {

    private final Collection<String> items;
    private ApplicationHomePage applicationHomePage;

    @Step("{0} opens the application on the home page")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(applicationHomePage),
                AddTodoItems.called(items)
        );
    }

    public static Start withAnEmptyTodoList() {
        return instrumented(Start.class, Collections.EMPTY_LIST);
    }
    public static Start withATodoListContaining(String... items) {
        return instrumented(Start.class, Arrays.asList(items));
    }
    public Start(Collection<String> items) { this.items = items; }
}