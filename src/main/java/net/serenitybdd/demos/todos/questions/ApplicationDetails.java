package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.demos.todos.model.ApplicationInformation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class ApplicationDetails implements Question<ApplicationInformation> {

    private final Target MAIN_HEADING = Target.the("main heading").locatedBy("css:h1");
    private final Target FOOTER = Target.the("footer").locatedBy("#info");

    @Override
    public ApplicationInformation answeredBy(Actor actor) {
        String title = BrowseTheWeb.as(actor).getTitle();
        String heading = Text.of(MAIN_HEADING).viewedBy(actor).value();
        String aboutInformation = Text.of(FOOTER).viewedBy(actor).value();

        return new ApplicationInformation(title, heading, aboutInformation);
    }
}
