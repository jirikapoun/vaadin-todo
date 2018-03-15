# ToDo – a Vaadin-Spring sample application

This app demonstrates the use of two major Java frameworks, Vaadin and Spring,
seamlessly together.

## Architecture overview

![Architecture diagram](https://raw.githubusercontent.com/kapoun/vaadin-todo/master/src/site/resources/todo-architecture.png)

The application follows the Model-View-Presenter pattern. Data model is
represented by two classes – Task, which holds data of a task, and TaskRepository,
which acts as a task repository. There is only one View-Presenter pair, TaskView
and TaskPresenter.

To demonstrate the use of Spring Web Services library, the application
implements one more feature – the IP address of a user is looked up in GeoIP
database and the user's country is logged into standard output. Logging is
provided by LoggingService, GeoIP lookup by GeoIPService.

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
