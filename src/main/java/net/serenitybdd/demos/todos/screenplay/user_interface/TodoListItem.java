package net.serenitybdd.demos.todos.screenplay.user_interface;

import net.serenitybdd.screenplay.targets.Target;

public class TodoListItem {

    public static Target COMPLETE_ITEM = Target.the("Complete item tick box")
                                              .locatedBy( "//*[@class='view' and contains(.,'{0}')]//input[@type='checkbox']");

    public static Target DELETE_ITEM = Target.the("Delete item button")
                                            .locatedBy( "//*[@class='view' and contains(.,'{0}')]//button[@class='destroy']");
}