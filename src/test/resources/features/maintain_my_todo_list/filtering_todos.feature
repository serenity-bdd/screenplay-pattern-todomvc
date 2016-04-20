@cucumber
Feature: Filtering todos

  In order to make me feel a sense of accomplishment
  As a forgetful person
  I want to be to view all of things I have completed

  Scenario: View only completed items
    Given that Jane has a todo list containing Buy some milk, Walk the dog
    And she has completed the task called 'Walk the dog'
    When she filters her list to show only Completed tasks
    Then her todo list should contain Walk the dog