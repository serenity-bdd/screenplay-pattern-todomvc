# Screenplay Pattern with Serenity BDD

This project is a simple demonstration showing the difference between
the [PageObject](http://martinfowler.com/bliki/PageObject.html)
and the [Screenplay pattern](https://dzone.com/articles/page-objects-refactored-solid-steps-to-the-screenp),
both implemented using the [Serenity BDD](http://serenity-bdd.info/#/) library and JUnit.

The web tests you'll find here run against the https://todomvc.com/examples/angular/dist/browser/#/all application and are organised
by feature in packages under `src/test/java/net/serenitybdd/demos/todos/<pattern name>/features`.

By default the tests run on Chrome, so make sure you have the latest chromedriver instance on your system path.
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

To run the project you'll need JDK 1.8 and Maven installed.

### Screenplay and Cucumber

The first demo shows the integration of Serenity BDD, Screenplay and Cucumber JVM.
To run it, execute:

```
mvn clean verify -Pcucumber
```

### Screenplay and JUnit

The second demo covers the integration of Serenity BDD, Screenplay and JUnit.

To run it, execute:

```
mvn clean verify -Pscreenplay
```

### Page Objects and JUnit

We've also included a demo of how the tests you've just seen implemented using the Screenplay Pattern
would have looked if they were implemented using the Page Objects.

To run it, execute:

```
mvn clean verify -Ppageobjects
```

## Reporting

The Serenity reports will be generated in the `target/site/serenity` directory.
