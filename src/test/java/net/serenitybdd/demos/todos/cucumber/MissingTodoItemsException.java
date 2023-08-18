package net.serenitybdd.demos.todos.cucumber;

import net.serenitybdd.core.di.SerenityInfrastructure;

public class MissingTodoItemsException extends AssertionError {
    public MissingTodoItemsException(String message) {
        super(message);
    }

    public MissingTodoItemsException(String message, Throwable cause) {
        super(message, cause);
    }
}
