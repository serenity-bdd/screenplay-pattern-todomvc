package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.exceptions.NoMatchingFilterButtonIsVisible;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pages.todolist.filter.FilterSelection;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class FilterItems implements Task {

    public static FilterItems byStatus(TodoStatusFilter status) {
        return instrumented(FilterItems.class, status);
    }

    private final TodoStatusFilter filter;

    protected FilterItems(TodoStatusFilter filter) {
        this.filter = filter;
    }

    @Step("{0} filters items by #filter")
    public <T extends Actor> void performAs(T theActor) {
        Target filterButton = FilterSelection.FILTER.of(filter.name())
                                                .called("filter by "+ filter);

        theActor.should(seeThat(the(filterButton), isVisible())
                        .orComplainWith(NoMatchingFilterButtonIsVisible.class,
                                        getMissingFilterButtonMessage()));

        theActor.attemptsTo(Click.on(filterButton));
    }

    public String getMissingFilterButtonMessage() {
        return String.format("Missing filter '%s'", filter);
    }
}
