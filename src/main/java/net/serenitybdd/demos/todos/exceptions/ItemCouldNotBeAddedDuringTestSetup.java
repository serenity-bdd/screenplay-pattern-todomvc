package net.serenitybdd.demos.todos.exceptions;

import net.serenitybdd.core.exceptions.CausesCompromisedTestFailure;

public class ItemCouldNotBeAddedDuringTestSetup extends Error implements CausesCompromisedTestFailure {

    public ItemCouldNotBeAddedDuringTestSetup(String message) {
        super(message);
    }

    public ItemCouldNotBeAddedDuringTestSetup(String message, Throwable cause) {
        super(message, cause);
    }
}
