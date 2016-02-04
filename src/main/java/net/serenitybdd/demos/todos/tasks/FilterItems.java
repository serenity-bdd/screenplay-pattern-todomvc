package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.demos.todos.user_interface.ToDoList.FILTER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FilterItems implements Task {

    private final TodoStatusFilter filter;

    @Step("{0} filters items by #filter")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(FILTER.of(filter.name()).called("filter by "+ filter)));
    }

    public static FilterItems byStatus(TodoStatusFilter status) {
        return instrumented(FilterItems.class, status);
    }
    public FilterItems(TodoStatusFilter filter) { this.filter = filter; }
}