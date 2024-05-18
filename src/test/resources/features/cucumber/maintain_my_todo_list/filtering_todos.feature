@filtering
@smoke-test
Feature: Filtering todos

  In order to make me feel a sense of accomplishment
  As a forgetful person
  I want to be to view all of things I have completed

  Scenario Outline: Viewing the items by status filter
    Given Jane has a todo list containing <tasks>
    And she completes the task called "<completed>"
    When she filters her list to show only <filter> tasks
    Then her todo list should contain <expected>
    Examples:
      | tasks                       | completed    | filter    | expected                     |
      | Buy some milk, Walk the dog | Walk the dog | Active    | Buy some milk                |
      | Buy some milk, Walk the dog | Walk the dog | Completed | Walk the dog                 |
      | Buy some milk, Walk the dog | Walk the dog | All       | Buy some milk,  Walk the dog |
