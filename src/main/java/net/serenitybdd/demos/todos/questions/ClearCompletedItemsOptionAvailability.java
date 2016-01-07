package net.serenitybdd.demos.todos.questions;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.demos.todos.pages.todolist.ClearCompleted;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Visibility;

import java.util.Map;

@Subject("the 'Clear Completed' option")
public class ClearCompletedItemsOptionAvailability implements Question<ElementAvailability> {

    private final Map<Boolean, ElementAvailability> ELEMENT_AVAILABILITY =
            ImmutableMap.of(
                    Boolean.TRUE, ElementAvailability.Available,
                    Boolean.FALSE, ElementAvailability.Unavailable
                    );

    @Override
    public ElementAvailability answeredBy(Actor actor) {
        Boolean clearCompleteButtonIsVisible = Visibility.of(ClearCompleted.BUTTON).viewedBy(actor).value();
        return ELEMENT_AVAILABILITY.get(clearCompleteButtonIsVisible);
    }
}
