package net.serenitybdd.demos.todos.screenplay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class TheUserName {

    static Target USER_NAME = Target.the("User name").locatedBy(".user-name");

    public static Question<String> value() {
        return actor -> Text.of(USER_NAME).viewedBy(actor).asString();
    }
}