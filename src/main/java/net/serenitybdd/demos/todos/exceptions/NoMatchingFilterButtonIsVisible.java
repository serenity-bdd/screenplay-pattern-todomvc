package net.serenitybdd.demos.todos.exceptions;

public class NoMatchingFilterButtonIsVisible extends AssertionError {

    public NoMatchingFilterButtonIsVisible(Object detailMessage) {
        super(detailMessage);
    }

    public NoMatchingFilterButtonIsVisible(String message, Throwable cause) {
        super(message, cause);
    }
}
