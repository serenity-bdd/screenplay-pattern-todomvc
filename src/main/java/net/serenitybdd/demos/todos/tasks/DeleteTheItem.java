package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.demos.todos.action.JSClick;
import net.serenitybdd.demos.todos.exceptions.NoDeleteButtonFound;
import net.serenitybdd.demos.todos.pages.todolist.items.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class DeleteTheItem implements Task {

    private final String itemName;

    protected DeleteTheItem(String itemName) {
        this.itemName = itemName;
    }

    @Step("{0} deletes the item '#itemName'")
    public <T extends Actor> void performAs(T theActor) {
        Target deleteButton = TodoListItem.DELETE_ITEM_BUTTON.of(itemName);

        theActor.should(seeThat(the(deleteButton), isVisible()).orComplainWith(NoDeleteButtonFound.class));

        theActor.attemptsTo(JSClick.on(deleteButton));
    }

    public static DeleteTheItem called(String itemName) {
        return instrumented(DeleteTheItem.class, itemName);
    }
}
