package cz.jkapoun.todo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import cz.jkapoun.todo.presenters.TodoPresenter;
import cz.jkapoun.todo.services.GeoIPService;
import cz.jkapoun.todo.services.LoggingService;
import cz.jkapoun.todo.services.TaskService;
import cz.jkapoun.todo.views.TodoView;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Theme("todo")
public class App extends UI {

  protected GeoIPService   geoIPService;
  protected LoggingService loggingService;
  protected TaskService    taskService;

  protected TodoView       todoView;
  protected TodoPresenter  todoPresenter;

  public App() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("cz.jkapoun.todo.model");

    geoIPService   = new GeoIPService(marshaller);
    loggingService = new LoggingService(geoIPService);
    taskService    = new TaskService();

    todoView       = new TodoView();
    todoPresenter  = new TodoPresenter(taskService, todoView);
  }

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    String ip = vaadinRequest.getRemoteAddr();
    loggingService.logIP(ip);
    
    taskService.addTask("Add a first task");
    setContent(todoView);
  }

}
