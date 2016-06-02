package net.serenitybdd.demos.todos.cucumber.actors;

import cucumber.api.java.After;

public class StageDirector {
    @After
    public void endTheAct() {
        OnStage.drawTheCurtain();
    }
}
