package net.serenitybdd.demos.todos.cucumber.actors;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;

import java.util.HashMap;
import java.util.Map;

public class Cast {
    private Map<String, Actor> actors = new HashMap<>();

    public Actor actorNamed(String actorName) {

        if (! Serenity.hasASessionVariableCalled(actorName)) {
            // todo: what about the abilities ??


            Serenity.setSessionVariable(actorName).to(Actor.named(actorName));
        }

        return Serenity.sessionVariableCalled(actorName);
    }
}
