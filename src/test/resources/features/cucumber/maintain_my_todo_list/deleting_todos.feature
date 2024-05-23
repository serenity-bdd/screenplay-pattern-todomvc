@cucumber
@deleting
Feature: Deleting todos

  In order to make me feel **a sense of accomplishment**
  As a tidy person
  I want to be to delete the tasks once I am done with them

  @smoke-test
  Scenario: Delete an active item
   Given Jane has a todo list containing Buy some milk, Walk the dog
    When she deletes the task called 'Walk the dog'
    Then her todo list should contain Buy some milk

  Scenario: Delete all the items
   Given Jane has a todo list containing Buy some milk, Walk the dog
    When she deletes the task called 'Walk the dog'
    And she deletes the task called 'Buy some milk'
    Then her todo list should be empty
