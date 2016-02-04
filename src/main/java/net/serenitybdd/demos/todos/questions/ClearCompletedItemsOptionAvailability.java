package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.user_interface.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Visibility;

import static serenityx.ValueOf.the;

@Subject("the 'Clear Completed' option")
public class ClearCompletedItemsOptionAvailability implements Question<ElementAvailability> {

    @Override
    public ElementAvailability answeredBy(Actor actor) {
        return ElementAvailability.from(
                the(Visibility.of(ToDoList.CLEAR_COMPLETED).viewedBy(actor))
        );
    }
}