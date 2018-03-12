package cz.jkapoun.todo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ComponentRenderer;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;

@Theme("todo")
public class App extends UI {

  protected VerticalLayout layout;
  protected TextField      newTaskField;
  protected Button         addTaskButton;
  protected Grid<Task>     taskGrid;

  protected HashMap<Integer, Task> tasks;

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    tasks = new HashMap<>();
    Task firstTask = new Task("Add a first task");
    tasks.put(firstTask.getId(), firstTask);

    layout = new VerticalLayout();
    setContent(layout);

    newTaskField = new TextField();
    newTaskField.setPlaceholder("Enter new task here");
    layout.addComponent(newTaskField);

    addTaskButton = new Button();
    addTaskButton.setCaption("Add");
    addTaskButton.addClickListener(event -> addTask(newTaskField.getValue()));
    layout.addComponent(addTaskButton);

    taskGrid = new Grid<>();
    taskGrid.setCaption("Tasks");
    taskGrid.addColumn(Task::getId)
            .setCaption("ID");
    taskGrid.addColumn(Task::getText)
            .setCaption("Text");
    taskGrid.addColumn(task -> new Button("×", event -> deleteTask(task.getId())), new ComponentRenderer())
            .setCaption("Delete");
    taskGrid.setItems(tasks.values());
    layout.addComponent(taskGrid);
  }

  protected void addTask(String text) {
    Task task  = new Task(text);
    Integer id = task.getId();
    tasks.put(id, task);

    newTaskField.clear();
    taskGrid.getDataProvider().refreshAll();
    Notification.show("Task added", Notification.Type.TRAY_NOTIFICATION);
  }

  protected void deleteTask(int id) {
    tasks.remove(id);

    newTaskField.clear();
    taskGrid.getDataProvider().refreshAll();
    Notification.show("Task deleted", Notification.Type.TRAY_NOTIFICATION);
  }

  @WebServlet(urlPatterns = "/*", name = "AppServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = App.class, productionMode = false)
  public static class AppServlet extends VaadinServlet {
  }
}
