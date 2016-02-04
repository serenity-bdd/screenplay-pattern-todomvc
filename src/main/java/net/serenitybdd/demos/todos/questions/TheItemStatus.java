package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.demos.todos.model.TodoStatus;
import net.serenitybdd.demos.todos.user_interface.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.SelectedStatus;
import net.serenitybdd.screenplay.targets.Target;

public class TheItemStatus implements Question<TodoStatus> {

    private final String itemName;

    public TheItemStatus(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public TodoStatus answeredBy(Actor actor) {
        Target completeItemButton = TodoListItem.COMPLETE_ITEM.of(itemName);

        Boolean itemChecked = SelectedStatus.of(completeItemButton).viewedBy(actor).as(Boolean.class);
        return TodoStatus.from(itemChecked);
    }

    public static Question<?> forTheItemCalled(String itemName) {
        return Instrumented.instanceOf(TheItemStatus.class).withProperties(itemName);
    }
}