package net.serenitybdd.demos.todos.features.accessing_the_application;

import net.serenitybdd.demos.todos.questions.ApplicationDetails;
import net.serenitybdd.demos.todos.questions.PlaceholderText;
import net.serenitybdd.demos.todos.tasks.Start;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.matchers.ConsequenceMatchers.displays;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static serenityx.GivenWhenThen.seeThat;

/**
 * This example illustrates using Serenity Steps with JUnit.
 */
@RunWith(SerenityRunner.class)
public class LearnAboutTheApplication {

    private Actor james = Actor.named("James");

    @Managed private WebDriver hisBrowser;
    @Steps private PlaceholderText thePlaceholderText;

    private ApplicationDetails theApplication = new ApplicationDetails();

    @Before
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }


    @Test
    public void application_should_be_clearly_identified_by_the_title() {

        givenThat(james).wasAbleTo(Start.withAnEmptyTodoList());

        then(james).should(
                seeThat(theApplication,
                        displays("title",equalTo("AngularJS • TodoMVC")),
                        displays("heading",equalTo("todos")),
                        displays("about", containsString("Credits"))
                )
        );
    }

    @Test
    public void should_display_a_meaningful_placeholder() {

        givenThat(james).wasAbleTo(Start.withAnEmptyTodoList());

        then(james).should(seeThat(thePlaceholderText, is("What needs to be done?")));
    }
}
