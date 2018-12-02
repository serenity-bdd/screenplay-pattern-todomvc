package net.serenitybdd.demos.todos.screenplay.actions;

import net.serenitybdd.markers.IsSilent;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class Refresh implements Interaction, IsSilent {
    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().manage().deleteAllCookies();
    }

    public static Refresh theBrowserSession() {
        return new Refresh();
    }
}
