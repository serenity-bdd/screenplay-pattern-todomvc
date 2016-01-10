package net.serenitybdd.demos.todos.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.demos.todos.exceptions.ItemCouldNotBeAddedDuringTestSetup;
import net.serenitybdd.demos.todos.pages.todolist.newitem.NewTodoForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

public class AddTheTodoItem implements Task {

    private final String thingToDo;

    protected AddTheTodoItem(String thingToDo) { this.thingToDo = thingToDo; }

    @Step("{0} adds a todo item called #thingToDo")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Enter.theValue(thingToDo)
                     .into(NewTodoForm.NEW_TODO_FIELD)
                        .thenHit(Keys.RETURN)
        );
        // Simulate application failure
        throw new ItemCouldNotBeAddedDuringTestSetup("Something went horribly wrong");
    }

    public static AddTheTodoItem called(String thingToDo) {
        return Instrumented.instanceOf(AddTheTodoItem.class).withProperties(thingToDo);
    }
}
