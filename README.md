# Serenity Journey Pattern demo

This project is a simple demonstration showing the difference between
the [PageObject](http://martinfowler.com/bliki/PageObject.html)
and the [Screenplay pattern](https://dzone.com/articles/page-objects-refactored-solid-steps-to-the-screenp),
both implemented using the [Serenity BDD](http://serenity-bdd.info/#/) library and JUnit.

The web tests you'll find here run against the http://todomvc.com/examples/angularjs/#/ application and are organised
by feature in packages under `src/test/java/net/serenitybdd/demos/todos/<pattern name>/features`.

## PageObjects implementation

These tests use pages and steps defined in `src/main/java/net/serenitybdd/demos/todos/pageobjects`.

The overall project structure is shown below:
````
+ model
    Domain model classes
+ pages
    PageObjects
+ steps
    Tasks and assertions performed by the "user"
````

## Screenplay implementation

These tests use tasks, actions, questions and page elements defined in `src/main/java/net/serenitybdd/demos/todos/screenplay`.

The overall project structure is shown below:
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

## Running the project

The project runs using JDK 1.8 and Maven. To run the demo, run:

```
mvn clean verify
```

The Serenity reports will be generated in the `target/site/serenity` directory.
