package cz.jkapoun.todo.views;

import cz.jkapoun.todo.model.Task;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ComponentRenderer;
import java.util.Collection;
import java.util.function.Consumer;

public class TodoView extends CustomComponent {

  protected VerticalLayout   layout;
  protected TextField        newTaskField;
  protected Button           addTaskButton;
  protected Grid<Task>       taskGrid;

  protected Consumer<String> addTaskHandler;
  protected Consumer<Task>   deleteTaskHandler;

  public TodoView() {
    initializeLayout();
  }

  public void setTasks(Collection<Task> tasks) {
    taskGrid.setItems(tasks);
  }

  public void setAddTaskHandler(Consumer<String> handler) {
    addTaskHandler = handler;
  }

  public void setDeleteTaskHandler(Consumer<Task> handler) {
    deleteTaskHandler = handler;
  }

  public void afterTaskAdded() {
    taskGrid.getDataProvider().refreshAll();
    newTaskField.clear();
    Notification.show("Task added", Notification.Type.TRAY_NOTIFICATION);
  }

  public void afterTaskDeleted() {
    taskGrid.getDataProvider().refreshAll();
    Notification.show("Task deleted", Notification.Type.TRAY_NOTIFICATION);
  }

  protected void initializeLayout() {
    layout = new VerticalLayout();
    setCompositionRoot(layout);

    newTaskField = new TextField();
    newTaskField.setPlaceholder("Enter new task here");
    layout.addComponent(newTaskField);

    addTaskButton = new Button();
    addTaskButton.setCaption("Add");
    addTaskButton.addClickListener(event -> onAddTask());
    layout.addComponent(addTaskButton);

    taskGrid = new Grid<>();
    taskGrid.setCaption("Tasks");
    taskGrid.addColumn(Task::getId)
            .setCaption("ID");
    taskGrid.addColumn(Task::getText)
            .setCaption("Text");
    taskGrid.addColumn(task -> new Button("×", event -> onDeleteTask(task)), new ComponentRenderer())
            .setCaption("Delete");
    layout.addComponent(taskGrid);
  }

  protected void onAddTask() {
    if (addTaskHandler != null)
      addTaskHandler.accept(newTaskField.getValue());
  }

  protected void onDeleteTask(Task task) {
    if (deleteTaskHandler != null)
      deleteTaskHandler.accept(task);
  }

}
