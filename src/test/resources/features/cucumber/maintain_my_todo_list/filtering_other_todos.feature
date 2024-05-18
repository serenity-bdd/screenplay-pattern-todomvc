@cucumber
@filtering
Feature: Filtering other todos

  In order to make me feel a sense of accomplishment
  As a forgetful person
  I want to be to view all of things I have completed

  @ios @android
  Scenario Outline: Filtering items by status
    Given Jane has prepared a todo list containing <tasks>
    And she has completed the task called "<completed>"
    When she filters the list to show only <filter> tasks
    Then her list should contain <expected>
    Examples:
      | tasks                       | completed    | filter    | expected                     |
      | Buy some milk, Walk the dog | Walk the dog | Active    | Buy some milk                |
#      | Buy some milk, Walk the dog | Broken       | Active    | Buy some milk                |
      | Buy some milk, Walk the dog | Walk the dog | Completed | Walk the dog                 |
      | Buy some milk, Walk the dog | Walk the dog | All       | Buy some milk,  Walk the dog |
