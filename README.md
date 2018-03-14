# ToDo - a Vaadin-Spring sample application

This app demonstrates the use of two major Java frameworks, Vaadin and Spring,
seamlessly together.

## Architecture overview

![Architecture diagram](https://raw.githubusercontent.com/kapoun/vaadin-todo/master/src/site/resources/todo-architecture.png)

## Running the application

### Prerequisites

In order to be able to run this application, you must have these dependencies
installed:

* Java 1.8 or higher
* Maven

For running the TestBench tests, you also need:

* Google Chrome
* ChromeDriver for Selenium

### Configuration

Before you build the application, you need to either disable TestBench tests (in
case you don't want to use them) or correctly set the ChromeDriver path. In that
case, please see `src/test/java/cz/jkapoun/todo/tests/AppIT.java` file.
