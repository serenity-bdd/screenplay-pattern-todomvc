package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pages.components.Filter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FilterItems implements Performable {

    public static FilterItems byStatus(TodoStatusFilter status) {
        return instrumented(FilterItems.class, status);
    }

    private final TodoStatusFilter filter;

    protected FilterItems(TodoStatusFilter filter) {
        this.filter = filter;
    }

    @Step("{0} filters items by #filter")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(Filter.by(filter)));
    }
}
