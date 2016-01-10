package net.serenitybdd.demos.todos.exceptions;

public class NoDeleteButtonFound extends AssertionError {

    public NoDeleteButtonFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDeleteButtonFound(Throwable cause) {
        super(cause);
    }
}
