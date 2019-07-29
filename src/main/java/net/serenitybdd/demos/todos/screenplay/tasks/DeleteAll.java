package net.serenitybdd.demos.todos.screenplay.tasks;

import net.serenitybdd.demos.todos.screenplay.actions.JSClick;
import net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.screenplay.user_interface.TodoList;
import net.serenitybdd.demos.todos.screenplay.user_interface.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteAll {

    public static Performable items() {
        return Task.where("{0} delets all items",
                FilterItems.toShow(TodoStatusFilter.All),
                Click.on(TodoList.TOGGLE_ALL_LABEL),
                Click.on(TodoList.CLEAR_COMPLETED)
        );
    }
}