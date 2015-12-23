package net.serenitybdd.demos.todos.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://todomvc.com/examples/angularjs/#/")
public class ApplicationHomePage extends PageObject {
    public static final Target TITLE = Target.the("application title").locatedBy("header h1");
}
