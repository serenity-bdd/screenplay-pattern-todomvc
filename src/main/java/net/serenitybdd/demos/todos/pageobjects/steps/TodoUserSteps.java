package net.serenitybdd.demos.todos.pageobjects.steps;

import net.serenitybdd.demos.todos.pageobjects.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pageobjects.pages.TodoListPage;
import net.thucydides.core.annotations.Step;

import static java.util.Arrays.asList;
import static net.serenitybdd.demos.todos.pageobjects.model.TodoStatus.Active;
import static net.serenitybdd.demos.todos.pageobjects.model.TodoStatus.Completed;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class TodoUserSteps {

    TodoListPage todoListPage;

    // -----------------------------------------------------------------------------------------------------------------
    // ACTIONS

    @Step
    public void starts_with_an_empty_todo_list() {
        starts_with_a_todo_list_containing();
    }

    @Step
    public void starts_with_a_todo_list_containing(String... actions) {
        todoListPage.openApplication();

        asList(actions).forEach(this::adds_an_action_called);
    }

    @Step
    public void adds_an_action_called(String actionName) {
        todoListPage.addAnActionCalled(actionName);
    }

    @Step
    public void completes_item_called(String action) {
        todoListPage.markAsComplete(action);
    }


    @Step
    public void deletes_item_called(String action) {
        todoListPage.delete(action);
    }

    @Step
    public void toggles_all_items() {
        todoListPage.toggleAll();
    }

    @Step
    public void clears_completed_items() {
        todoListPage.clearCompletedActions();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ASSERTIONS

    @Step
    public void should_see_that_the_placeholder_text_says(String expectedPlaceholderText) {
        assertThat(todoListPage.placeholderText(), is(expectedPlaceholderText));
    }

    @Step
    public void should_see_that_that_following_items_are_marked_as_complete(String... actions) {
        asList(actions).forEach(this::should_see_that_that_following_item_is_marked_as_complete);
    }

    @Step
    public void should_see_that_that_following_item_is_marked_as_complete(String action) {
        assertThat(todoListPage.statusOf(action), is(Completed));
    }

    @Step
    public void should_see_that_that_following_items_are_marked_as_active(String... actions) {
        asList(actions).forEach(this::should_see_that_that_following_items_is_marked_as_active);
    }

    @Step
    private void should_see_that_that_following_items_is_marked_as_active(String action) {
        assertThat(todoListPage.statusOf(action), is(Active));
    }

    @Step
    public void should_see_that_the_number_of_items_left_is(int expectedCount) {
        assertThat(todoListPage.itemsLeftCount(), is(expectedCount));
    }

    @Step
    public void should_see_that_the_clear_completed_items_option_is_not_visible() {
        assertThat(todoListPage.canClearCompletedActions(), is(false));
    }

    @Step
    public void filters_items_to_show(TodoStatusFilter filter) {
        todoListPage.filterByStatus(filter);
    }

    @Step
    public void should_see_that_the_currently_selected_filter_is(TodoStatusFilter filter) {
        assertThat(todoListPage.currentlySelectedFilter().equals(filter), is(true));
    }

    @Step
    public void should_see_that_displayed_items_contain(String... actions) {
        assertThat(todoListPage.actions(), hasItems(actions));
    }

    @Step
    public void should_see_that_displayed_items_do_not_contain(String... actions) {
        assertThat(todoListPage.actions(), not(contains(actions)));
    }

    @Step
    public void should_see_the_correct_website_title() {
        assertThat(todoListPage.getTitle(), is("AngularJS • TodoMVC"));
    }

    @Step
    public void should_see_the_correct_application_heading() {
        assertThat(todoListPage.heading(), is("todos"));
    }

    @Step
    public void should_see_the_about_section() {
        assertThat(todoListPage.footer(), containsString("Credits"));
    }
}
