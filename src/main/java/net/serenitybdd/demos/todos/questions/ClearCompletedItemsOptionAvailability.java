package net.serenitybdd.demos.todos.questions;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.demos.todos.pages.components.ClearCompleted;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.Map;

@Subject("the 'Clear Completed' option")
public class ClearCompletedItemsOptionAvailability implements Question<ElementAvailability> {

    ClearCompleted clearCompleted;

    private final Map<Boolean, ElementAvailability> ELEMENT_AVAILABILITY =
            ImmutableMap.of(
                    Boolean.TRUE, ElementAvailability.Available,
                    Boolean.FALSE, ElementAvailability.Unavailable
                    );

    @Override
    public ElementAvailability answeredBy(Actor actor) {
        return ELEMENT_AVAILABILITY.get(clearCompleted.isVisible());
    }
}
