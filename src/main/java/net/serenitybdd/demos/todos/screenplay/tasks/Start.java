package net.serenitybdd.demos.todos.screenplay.tasks;

import com.google.common.base.Joiner;
import net.serenitybdd.demos.todos.screenplay.user_interface.ApplicationHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Unless;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Start implements Task {

    private final Collection<String> items;
    private ApplicationHomePage applicationHomePage;
    private final String todoListDescription;

    @Step("{0} starts with #todoListDescription")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(applicationHomePage),
                Unless.the(items.isEmpty(), AddTodoItems.called(items)));
    }

    public static Start withAnEmptyTodoList() {
        return instrumented(Start.class, Collections.EMPTY_LIST, "no items");
    }
    public static Start withATodoListContaining(String... items) {
        return instrumented(Start.class, Arrays.asList(items), "a todo list containing " +  Joiner.on(", ").join(items));
    }

    public Start(Collection<String> items, String todoListDescription) {
        this.items = items;
        this.todoListDescription = todoListDescription;
    }
}