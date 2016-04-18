package net.serenitybdd.demos.todos.screenplay.tasks;

import com.google.common.collect.ImmutableList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddTodoItems implements Task {

    private final List<String> todos;

    @Step("{0} adds the todo items called: #todos")
    public <T extends Actor> void performAs(T actor) {
        todos.forEach(
                todo -> actor.attemptsTo(AddATodoItem.called(todo))
        );
    }

    public static AddTodoItems called(String... items) {
        return instrumented(AddTodoItems.class, asList(items));
    }

    public static AddTodoItems called(Collection<String> items) {
        return instrumented(AddTodoItems.class, items);
    }
    public AddTodoItems(List<String> items) { this.todos = ImmutableList.copyOf(items); }
}