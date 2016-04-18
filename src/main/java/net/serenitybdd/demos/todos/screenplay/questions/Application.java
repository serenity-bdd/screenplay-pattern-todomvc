package net.serenitybdd.demos.todos.screenplay.questions;

import net.serenitybdd.demos.todos.screenplay.model.ApplicationInformation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.questions.ValueOf.the;

public class Application implements Question<ApplicationInformation> {

    private final Target MAIN_HEADING = Target.the("main heading").locatedBy("css:h1");
    private final Target FOOTER = Target.the("footer").locatedBy("#info");

    @Override
    public ApplicationInformation answeredBy(Actor actor) {
        String title = BrowseTheWeb.as(actor).getTitle();
        String heading = the(Text.of(MAIN_HEADING).viewedBy(actor));
        String aboutInformation = the(Text.of(FOOTER).viewedBy(actor));

        return new ApplicationInformation(title, heading, aboutInformation);
    }

    public static Application information() {
        return new Application();
    }
}