Filtering todos

Narrative:
In order to make me feel a sense of accomplishment
As a forgetful person
I want to be to view all of things I have completed

Scenario: View only completed items
Given that Jane has a todo list containing Buy some milk, Walk the dog
And she has completed the task called 'Walk the dog'
When she filters her list to show only Completed tasks
Then the todo list should contain Walk the dog

Scenario: Do other things
Given that Jane has a todo list containing <tasks>
And she has completed the task called 'Walk the cat'
When she filters her list to show only <filter> tasks
Then the todo list should contain <expected>
Examples:
| tasks                        | filter    | expected       |
| Buy some bread, Walk the cat | Completed | Walk the cat   |
| Buy some bread, Walk the cat | Active    | Buy some bread |
