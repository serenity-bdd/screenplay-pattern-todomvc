package net.serenitybdd.demos.todos.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Complete {

    public static CompleteItem itemCalled(String itemName) {
        return instrumented(CompleteItem.class, itemName);
    }
    public static CompleteAllItems allItems() {
        return instrumented(CompleteAllItems.class);
    }

}
