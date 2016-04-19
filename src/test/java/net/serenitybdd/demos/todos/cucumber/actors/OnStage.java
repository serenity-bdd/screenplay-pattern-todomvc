package net.serenitybdd.demos.todos.cucumber.actors;

import net.serenitybdd.screenplay.Actor;

public class OnStage {

    private static final Cast cast = new Cast();

    public static Actor theActorCalled(String actorName) {
        return cast.actorNamed(actorName);
    }

    public static Actor theActorInTheSpotlight() {
        return null;
    }
}
