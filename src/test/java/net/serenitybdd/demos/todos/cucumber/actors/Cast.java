package net.serenitybdd.demos.todos.cucumber.actors;

import com.google.common.collect.Maps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.webdriver.WebdriverManager;

import java.util.Map;

public class Cast {

    private WebdriverManager manager = Injectors.getInjector().getInstance(WebdriverManager.class);

    Map<String, Actor> actors = Maps.newHashMap();

    public Actor actorNamed(String actorName) {

        if (! actors.containsKey(actorName)) {
            Actor newActor = Actor.named(actorName);

            newActor.can(BrowseTheWeb.with(manager.getWebdriverByName(actorName)));

            actors.put(actorName, newActor);
        }
        return actors.get(actorName);
    }
}
