package net.serenitybdd.demos.todos.screenplay.completing_todos;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.demos.todos.screenplay.questions.TheItemStatus;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.CompleteItem;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.demos.todos.screenplay.tasks.ToggleStatus;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.demos.todos.screenplay.model.TodoStatus.Completed;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("Screenplay")
public class CompleteATodo {

    private Actor james = Actor.named("James");

    @Managed
    private WebDriver driver;

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(driver));
    }

    @Test
    public void should_be_able_to_complete_a_todo_using_imperative_code() {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        driver.findElement(By.cssSelector(".new-todo")).sendKeys("Walk the dog", Keys.ENTER);
        driver.findElement(By.cssSelector(".new-todo")).sendKeys("Put out the garbage", Keys.ENTER);
        driver.findElement(
                        By.xpath("//*[@class='view' and contains(.,'Walk the dog')]//input[@type='checkbox']"))
                .click();

        boolean completeItemButtonSelected =
                driver.findElement(
                                By.xpath("//*[@class='view' and contains(.,'Walk the dog')]//input[@type='checkbox']"))
                        .isSelected();

        assertTrue(completeItemButtonSelected);

        String itemCount = driver.findElement(By.cssSelector(".todo-count strong")).getText();
        assertThat(itemCount).isEqualTo("1");
    }

    private static class TodoMVC {
        static By NEW_TODO_FIELD = By.cssSelector(".new-todo");
        static By ITEMS = By.cssSelector(".todo-list li");
        static By ITEM_LABELS = By.cssSelector(".todo-list li label");
        static By ITEM_CHECKBOXES = By.cssSelector(".todo-list li .toggle");
        static By ITEM_DELETE_BUTTONS = By.cssSelector(".todo-list li .destroy");
        static By CLEAR_COMPLETED_BUTTON = By.cssSelector(".clear-completed");
        static By TOGGLE_ALL_BUTTON = By.cssSelector(".toggle-all");
        static By ITEMS_LEFT_COUNT = By.cssSelector(".todo-count strong");
        static By FILTERS = By.cssSelector(".filters li a");

        private WebDriver driver;

        private TodoMVC(WebDriver driver) {
            this.driver = driver;
        }

        public void open() {
            driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        }

        public void enterTodoField(String todoName) {
            driver.findElement(NEW_TODO_FIELD).sendKeys(todoName, Keys.ENTER);
        }

        public void completeItemCalled(String itemName) {
            driver.findElement(By.xpath("//label[.='" + itemName + "']/../input")).click();
        }

        public WebElement itemCheckboxFor(String itemName) {
            return driver.findElement(By.xpath("//label[.='" + itemName + "']/../input"));
        }

        public String getItemsLeftCount() {
            return driver.findElement(ITEMS_LEFT_COUNT).getText();
        }

        public void clickEnter() {
        }
    }

    @Test
    public void should_be_able_to_complete_a_todo_using_a_bdd_style() {

        givenThat(james).wasAbleTo(
                Start.withATodoListContaining("Walk the dog", "Put out the garbage")
        );
        when(james).attemptsTo(
                CompleteItem.called("Walk the dog")
        );
        then(james).should(
                seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Completed)),
//                seeThat(TheItems.leftCount(), is(1))
                seeThat(TheItems.left(), is(1))
        );
    }

    @Test
    public void should_be_able_to_complete_a_todo_using_page_objects() {
        TodoMVC todoMVC = new TodoMVC(driver);
        todoMVC.open();
        todoMVC.enterTodoField("Walk the dog");
        todoMVC.clickEnter();
        todoMVC.enterTodoField("Put out the garbage");
        todoMVC.clickEnter();
        assertThat(todoMVC.getItemsLeftCount()).isEqualTo("2");
        todoMVC.itemCheckboxFor("Walk the dog").click();
        assertThat(todoMVC.getItemsLeftCount()).isEqualTo("1");
    }

    @Test
    public void should_be_able_to_complete_a_todo() {
        james.attemptsTo(
                Start.withATodoListContaining("Walk the dog", "Put out the garbage"),
                CompleteItem.called("Walk the dog"),
                Ensure.that(TheItemStatus.forTheItemCalled("Walk the dog")).isEqualTo(Completed),
                Ensure.that(TheItems.leftCount()).isEqualTo(1)
        );
    }

    @Test
    public void should_be_able_to_toggle_the_status_of_all_todos() {
        james.attemptsTo(
                Start.withATodoListContaining("Walk the dog", "Put out the garbage"),
                ToggleStatus.ofAllItems(),

                Ensure.that(TheItemStatus.forTheItemCalled("Walk the dog")).isEqualTo(Completed),
                Ensure.that(TheItemStatus.forTheItemCalled("Put out the garbage")).isEqualTo(Completed),
                Ensure.that(TheItems.leftCount()).isEqualTo(0)
        );
    }


    @Test
    public void should_see_the_number_of_todos_decrease_when_an_item_is_completed() {

        givenThat(james).wasAbleTo(
                Start.withATodoListContaining("Walk the dog", "Put out the garbage")
        );

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog")
        );

        then(james).should(seeThat(TheItems.leftCount(), is(1)));
    }
}
