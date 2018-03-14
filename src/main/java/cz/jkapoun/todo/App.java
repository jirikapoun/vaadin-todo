package cz.jkapoun.todo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import cz.jkapoun.todo.presenters.TodoPresenter;
import cz.jkapoun.todo.services.GeoIPService;
import cz.jkapoun.todo.services.LoggingService;
import cz.jkapoun.todo.services.TaskService;
import cz.jkapoun.todo.views.TodoView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Main UI of the application.
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@SpringUI
@Theme("todo")
public class App extends UI {

  protected GeoIPService   geoIPService;
  protected LoggingService loggingService;
  protected TaskService    taskService;

  protected TodoView       todoView;
  protected TodoPresenter  todoPresenter;

  @Autowired
  public App(GeoIPService geoIPService, LoggingService loggingService, TaskService taskService, TodoView todoView, TodoPresenter todoPresenter) {
    this.geoIPService   = geoIPService;
    this.loggingService = loggingService;
    this.taskService    = taskService;

    this.todoView       = todoView;
    this.todoPresenter  = todoPresenter;
  }

  /**
   * Instructs LoggingService to log user's IP address and loads TodoView.
   */
  @Override
  protected void init(VaadinRequest vaadinRequest) {
    String ip = vaadinRequest.getRemoteAddr();
    loggingService.logRequest(ip);
    
    taskService.addTask("Add a first task");
    setContent(todoView);
  }

}
