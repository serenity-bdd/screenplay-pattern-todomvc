package net.serenitybdd.demos.todos.exceptions;

import net.serenitybdd.core.exceptions.CausesCompromisedTestFailure;

public class ApplicationNotAvailable extends Error implements CausesCompromisedTestFailure {

    public ApplicationNotAvailable(String message) {
        super(message);
    }

    public ApplicationNotAvailable(String message, Throwable cause) {
        super(message, cause);
    }
}
