package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.demos.todos.pages.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

public class AddATodoItem implements Task {

    private final String thingToDo;

    protected AddATodoItem(String thingToDo) { this.thingToDo = thingToDo; }

    @Step("{0} adds a todo item called #thingToDo")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Enter.theValue(thingToDo)
                     .into(ToDoList.WHAT_NEEDS_TO_BE_DONE)
                        .thenHit(Keys.RETURN)
        );
    }

    public static AddATodoItem called(String thingToDo) {
        return Instrumented.instanceOf(AddATodoItem.class).withProperties(thingToDo);
    }
}
