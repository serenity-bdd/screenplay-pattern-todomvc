package net.serenitybdd.demos.todos.tasks;

import com.google.common.collect.ImmutableList;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static java.util.Arrays.asList;

public class AddTodoItems implements Task {

    private final List<String> todos;

    protected AddTodoItems(List<String> items) { this.todos = ImmutableList.copyOf(items); }

    @Step("{0} adds the todo items called #todos")
    public <T extends Actor> void performAs(T actor) {
        todos.forEach(
                todo -> actor.attemptsTo(AddATodoItem.called(todo))
        );
    }

    public static AddTodoItems called(String... items) {
        return Instrumented.instanceOf(AddTodoItems.class).withProperties(asList(items));
    }
}
