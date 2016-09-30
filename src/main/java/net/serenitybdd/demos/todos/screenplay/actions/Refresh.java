package net.serenitybdd.demos.todos.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class Refresh implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().manage().deleteAllCookies();
        //BrowseTheWeb.as(actor).getDriver().navigate().refresh();
    }

    public static Refresh theBrowserSession() {
        return new Refresh();
    }
}
