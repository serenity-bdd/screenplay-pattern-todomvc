package serenityx;

import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.QuestionConsequence;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.allOf;

public class GivenWhenThen {
    @SafeVarargs
    public static <T> Consequence<T> seeThat(Question<T> actual, Matcher<T>... expected) {
        return new QuestionConsequence<>(actual, allOf(expected));
    }
}