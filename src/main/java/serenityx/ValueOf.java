package serenityx;

import net.serenitybdd.screenplay.questions.UIState;

public class ValueOf {
    public static <OUTPUT> OUTPUT the(UIState<OUTPUT> answer) {
        return answer.value();
    }
}
