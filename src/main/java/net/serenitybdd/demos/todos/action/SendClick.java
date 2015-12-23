package net.serenitybdd.demos.todos.action;

import net.serenitybdd.core.targets.Target;
import net.serenitybdd.screenplay.Action;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.abilities.BrowseTheWeb.as;

public class SendClick implements Action {

    private final Target target;

    public SendClick(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} clicks on #target")
    public <T extends Actor> void performAs(T theActor) {
        WebElement deleteButton = as(theActor).findBy(pathTo(target));
        as(theActor).evaluateJavascript("arguments[0].click()", deleteButton);
    }

    public static Action to(Target target) {
        return instrumented(SendClick.class, target);
    }

    private String pathTo(Target target) {
        return target.getCssOrXPathSelector();
    }

}
