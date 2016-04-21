package net.serenitybdd.demos.todos.cucumber.actors;

import net.serenitybdd.screenplay.Actor;

import java.util.function.Supplier;

public class OnStage implements Supplier<Stage> {

    private static final ThreadLocal<Stage> stage = ThreadLocal.withInitial(new OnStage());

    public static Actor theActorCalled(String requiredActor) {
        return stage().shineSpotlightOn(requiredActor);
    }

    public static Actor theActorInTheSpotlight() {
        return stage().theActorInTheSpotlight();
    }

    @Override
    public Stage get() {
        return new Stage(new Cast());
    }

    private static Stage stage() { return stage.get(); }

    public static void drawTheCurtain() {
        stage().drawTheCurtain();
    }
}
