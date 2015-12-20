package net.serenitybdd.demos.todos.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.demos.todos.pages.components.ToDoList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.stream.Collectors;

@Subject("the displayed todo items")
public class DisplayedItems implements Question<List<String>> {

    public static DisplayedItems theDisplayedItems() {
        return new DisplayedItems();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).findAll(ToDoList.TODO_ITEMS).stream()
                                     .map(WebElementFacade::getText)
                                     .collect(Collectors.toList());
    }
}
