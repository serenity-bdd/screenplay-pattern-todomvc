package net.serenitybdd.demos.todos.pageobjects.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.demos.todos.pageobjects.model.TodoStatus;
import net.serenitybdd.demos.todos.pageobjects.model.TodoStatusFilter;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@DefaultUrl("http://todomvc.com/examples/angularjs/#/")
public class TodoListPage extends PageObject {

    // -----------------------------------------------------------------------------------------------------------------
    // SELECTORS

    private static final String MAIN_HEADING         = "css:h1";
    private static final String FOOTER               = "#info";
    private static final String NEW_TODO_INPUT_FIELD = "#new-todo";
    private static final String ACTION_ROW           = "//div[@class='view' and contains(.,'%s')]";
    private static final String ACTION_ROW_LABEL     = "//label[contains(.,'%s')]";
    private static final String COMPLETE_TICKBOX     = ".//input[@ng-model='todo.completed']";
    private static final String DELETE_BUTTON        = "//button[@class='destroy']";
    private static final String FILTERS              = "#filters";
    private static final String SELECTED_FILTER      = "#filters li .selected";
    private static final String ITEMS_LEFT_COUNT     = "#todo-count strong";
    private static final String TOGGLE_ALL           = "#toggle-all";
    private static final String CLEAR_COMPLETED      = "#clear-completed";

    // -----------------------------------------------------------------------------------------------------------------
    // STEPS

    public void openApplication() {
        open();
        waitForTheApplicationToLoad();
    }

    private void waitForTheApplicationToLoad() {
        withTimeoutOf(60, TimeUnit.SECONDS).waitFor(NEW_TODO_INPUT_FIELD);
    }

    public void addAnActionCalled(String actionName) {
        $(NEW_TODO_INPUT_FIELD).type(actionName)
                .then().sendKeys(Keys.ENTER);
    }

    public void filterByStatus(TodoStatusFilter status) {
        findBy(FILTERS)
                .then().findBy(statusFilterLinkFor(status))
                .then().click();
    }

    private String statusFilterLinkFor(TodoStatusFilter status) {
        return String.format(".//a[.='%s']", status.name());
    }

    public void markAsComplete(String action) {
        inActionRowFor(action).findBy(COMPLETE_TICKBOX).click();
    }

    public void delete(String action) {
        evaluateJavascript("arguments[0].click()", inActionRowLabelFor(action).findBy(DELETE_BUTTON));
    }

    private WebElementFacade inActionRowFor(String action) {
        return $(String.format(ACTION_ROW, action));
    }

    private WebElementFacade inActionRowLabelFor(String action) {
        return $(String.format(ACTION_ROW_LABEL, action));
    }

    private boolean isShownAsCompleted(WebElementFacade actionRow) {
        return actionRow.find(By.tagName("label")).getCssValue("text-decoration").equals("line-through");
    }

    public void updateAction(String currentActionName, String newActionName) {
        evaluateJavascript("arguments[0].click()", inActionRowLabelFor(currentActionName));
        inActionRowLabelFor(currentActionName).type(newActionName);
        inActionRowLabelFor(currentActionName).sendKeys(Keys.RETURN);
    }

    public void clearCompletedActions() {
        $(CLEAR_COMPLETED).click();
    }

    public void toggleAll() {
        $(TOGGLE_ALL).click();
    }


    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS

    public String heading() {
        return $(MAIN_HEADING).getText();
    }

    public String placeholderText() {
        return $(NEW_TODO_INPUT_FIELD).getAttribute("placeholder");
    }

    public int itemsLeftCount() {
        return Integer.valueOf($(ITEMS_LEFT_COUNT).getText());
    }

    public boolean canClearCompletedActions() {
        return $(CLEAR_COMPLETED).isCurrentlyVisible();
    }

    public TodoStatus statusOf(String action) {
        WebElementFacade actionRow = inActionRowFor(action);
        return isShownAsCompleted(actionRow) ? TodoStatus.Completed : TodoStatus.Active;
    }

    public TodoStatusFilter currentlySelectedFilter() {
        return TodoStatusFilter.valueOf(findBy(SELECTED_FILTER).getText());
    }

    public List<String> actions() {
        return findAll(".view").stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public String footer() {
        return $(FOOTER).getText();
    }
}
