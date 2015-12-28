package net.serenitybdd.demos.todos.pages.todolist.items;

import net.serenitybdd.screenplay.targets.Target;

public class TodoListItem {

    public static Target COMPLETE_ITEM_BUTTON = Target.the("Complete button")
                                              .locatedBy( "//*[@class='view' and contains(.,'{0}')]//input[@type='checkbox']");

    public static Target DELETE_ITEM_BUTTON = Target.the("Delete button")
                                            .locatedBy( "//*[@class='view' and contains(.,'{0}')]//button[@class='destroy']");

    public static Target ITEM_TEXT_LABEL = Target.the("Item text label")
                                          .locatedBy("//*[@class='view' and contains(.,'{0}')]//label");

    public static Target ITEM_TEXT_INPUT = Target.the("Item text label")
                                          .locatedBy("//li[contains(.,'{0}')]//form/input");
}
