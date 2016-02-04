package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.demos.todos.user_interface.ToDoList.WHAT_NEEDS_TO_BE_DONE;
import static org.openqa.selenium.Keys.RETURN;

public class AddATodoItem implements Task {

    private final String thingToDo;

    @Step("{0} adds a todo item called #thingToDo")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Enter.theValue(thingToDo)
                    .into(WHAT_NEEDS_TO_BE_DONE)
                    .thenHit(RETURN)
        );
    }

    public static AddATodoItem called(String thingToDo) {
        return Instrumented.instanceOf(AddATodoItem.class).withProperties(thingToDo);
    }
    public AddATodoItem(String thingToDo) { this.thingToDo = thingToDo; }
}