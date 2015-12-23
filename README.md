# Serenity Journey Pattern demo

This application is a simple demonstration project using Serenity and the Journey Pattern with JUnit, running tests against the http://todomvc.com/examples/angularjs/#/ application.

The project has a number of web tests, organised by feature in packages under `src/test/java/net/serenitybdd/demos/todos/features`. These tests use tasks, actions, questions and page elements defined in `src/main/java/net/serenitybdd/demos/todos`.
The overall project structure is shown here:
````
+ model
    Domain model classes
+ tasks
    Business-level tasks
+ action
    UI interactions
+ pages
    Page Objects and Page Elements
+ questions
    Objects used to query the application
````

The project runs using JDK 1.8 and Maven. To run the demo, run:

```
mvn clean verify
```

The Serenity reports will be generated in the `target/site/serenity` directory.
