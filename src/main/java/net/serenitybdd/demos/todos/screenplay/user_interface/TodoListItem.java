package net.serenitybdd.demos.todos.screenplay.user_interface;

import net.serenitybdd.screenplay.targets.Target;

public class TodoListItem {

    public static Target ITEM_LABEL    =  Target.the("Item label")
            .locatedBy("//*[@class='view' and contains(.,'{0}')]//label");

    public static Target COMPLETE_ITEM = Target.the("Complete item tick box")
            .locatedBy("//*[@class='view' and contains(.,'{0}')]//input[@type='checkbox']");

    public static Target EDIT_ITEM     = Target.the("Edit item field")
            .locatedBy("//li[*[@class='view' and contains(.,'{0}')]]//input[contains(@class, 'edit')]");

    public static Target DELETE_ITEM   = Target.the("Delete item button")
            .locatedBy("//*[@class='view' and contains(.,'{0}')]//button[@class='destroy']");
}