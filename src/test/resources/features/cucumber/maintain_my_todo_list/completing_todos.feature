@cucumber
@completing
Feature: Completing todos

  In order to make me feel **a sense of accomplishment**
  As a forgetful person
  I want to be to _view all of things I have completed_

  @smoke-test
  Scenario: When a task is completed it remains in the main list
    Given Jane has a todo list containing Buy some milk, Walk the dog
    When she completes the task called "Walk the dog"
    Then her todo list should contain Buy some milk, Walk the dog
    But the "Walk the dog" task should be shown as Completed

  Scenario: Completed tasks should appear in the Completed list
    Given James has a todo list containing Buy some milk, Walk the dog
    When he completes the task called "Walk the dog"
    And he filters her list to show only Completed tasks
    Then her todo list should contain Walk the dog

  Scenario Outline: Completed tasks should be shown as Completed
    Given Sarah has a todo list containing <Initial Tasks>
    When she completes the task called "Walk the dog"
    Then the "<Task>" task should be shown as <Final Status>
    Examples:
      | Initial Tasks               | Task          | Final Status |
      | Buy some milk, Walk the dog | Walk the dog  | Completed    |
      | Walk the dog                | Walk the dog  | Completed    |
      | Buy some milk, Walk the dog | Buy some milk | Active       |

  Scenario: The list of completed items should be empty if nothing has been completed
    Given Jill has a todo list containing Buy some milk, Walk the dog
    When she filters her list to show only Completed tasks
    Then her todo list should be empty

  Scenario: The todo count should keep track of how many todos remain
    Given Sophie has a todo list containing Buy some milk, Walk the dog
    Then her remaining todo count should be 2
    When she completes the task called "Walk the dog"
    Then her remaining todo count should be 1
