@cucumber
@issues:MYPROJ-123,MYPROJ-456
Feature: Filtering todos

  In order to make me feel a sense of accomplishment
  As a forgetful person
  I want to be to view all of things I have completed

  @priority:musthave
  Scenario: View only completed items
    Given that Jane has a todo list containing Buy some milk, Walk the dog
    And she has completed the task called 'Walk the dog'
    When she filters her list to show only Completed tasks
    Then her todo list should contain Walk the dog

  @fast
  @issues:MYPROJ-123
  Scenario: Make some coffee
    Given that Jane wants some coffee
    When she makes some coffee
    Then she should be able to drink the coffee

  @Manual
  Scenario Outline: Do other things
    Given that Jane has a todo list containing <tasks>
    And she has completed the task called 'Walk the dog'
    When she filters her list to show only <filter> tasks
    Then her todo list should contain <expected>
    Examples:
      | tasks                       | filter    | expected      |
      | Buy some milk, Walk the dog | Completed | Walk the dog  |
      | Buy some milk, Walk the dog | Active    | Buy some milk |
