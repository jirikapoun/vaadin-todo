package cz.jkapoun.todo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import cz.jkapoun.todo.model.TaskRepository;
import cz.jkapoun.todo.presenters.TodoPresenter;
import cz.jkapoun.todo.services.LoggingService;
import cz.jkapoun.todo.services.StdoutLoggingService;
import cz.jkapoun.todo.views.TodoView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Main UI of the application.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@SpringUI
@Theme("todo")
public class App extends UI {

  protected TodoView       todoView;
  protected TodoPresenter  todoPresenter;
  protected LoggingService loggingService;

  @Autowired
  public App(StdoutLoggingService loggingService, TaskRepository taskService, TodoView todoView, TodoPresenter todoPresenter) {
    this.loggingService = loggingService;
    this.todoView       = todoView;
    this.todoPresenter  = todoPresenter;
  }

  /**
   * Instructs StdoutLoggingService to log user's IP address and loads TodoView.
   */
  @Override
  protected void init(VaadinRequest vaadinRequest) {
    String ip = vaadinRequest.getRemoteAddr();
    loggingService.logRequest(ip);
    
    setContent(todoView);
  }

}
