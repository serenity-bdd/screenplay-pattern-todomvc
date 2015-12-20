package net.serenitybdd.demos.todos.questions;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.demos.todos.model.TodoStatus;
import net.serenitybdd.demos.todos.pages.components.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.serenitybdd.demos.todos.model.TodoStatus.Active;
import static net.serenitybdd.demos.todos.model.TodoStatus.Completed;

public class TheItemStatus implements Question<TodoStatus> {

    private final String itemName;

    private final Map<Boolean, TodoStatus> CHECKED_STATUS = ImmutableMap.of(
        FALSE, Active,
        TRUE,  Completed
    );

    public TheItemStatus(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public TodoStatus answeredBy(Actor actor) {
        return statusFrom(BrowseTheWeb.as(actor).onPage(ToDoList.class).isChecked(itemName));
    }

    private TodoStatus statusFrom(Boolean checked) {
        return CHECKED_STATUS.get(checked);
    }

    public static Question<?> forTheItemCalled(String itemName) {
        return Instrumented.instanceOf(TheItemStatus.class).withProperties(itemName);
    }
}
