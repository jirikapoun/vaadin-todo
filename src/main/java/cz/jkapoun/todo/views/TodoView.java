package cz.jkapoun.todo.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ComponentRenderer;
import com.vaadin.ui.themes.ValoTheme;
import cz.jkapoun.todo.model.Task;
import cz.jkapoun.todo.presenters.TodoPresenter;
import java.util.Collection;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

/**
 * View class of the main application UI.
 * Manages the Vaadin components shown to the user. Also provides the way for
 * the presenter to subscribe to UI events (button clicks in this case).
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 * @see    TodoPresenter
 */
@Component
@UIScope
public class TodoView extends CustomComponent {

  protected VerticalLayout       layout;
  protected HorizontalLayout     sublayout;
  protected Label                headerLabel;
  protected TextField            newTaskField;
  protected Button               addTaskButton;
  protected Grid<Task>           taskGrid;
  protected Button.ClickShortcut addTaskShortcut;

  protected Consumer<String> addTaskHandler;
  protected Consumer<Task>   deleteTaskHandler;

  public void init() {
    addTaskShortcut = new Button.ClickShortcut(addTaskButton, ShortcutAction.KeyCode.ENTER);

    headerLabel = new Label();
    headerLabel.setValue("ToDo");
    headerLabel.addStyleName(ValoTheme.LABEL_H1);

    newTaskField = new TextField();
    newTaskField.setPlaceholder("Enter new task here");
    newTaskField.setWidth("100%");
    newTaskField.addFocusListener(event -> newTaskField.addShortcutListener(addTaskShortcut));
    newTaskField.addBlurListener (event -> newTaskField.removeShortcutListener(addTaskShortcut));
    
    addTaskButton = new Button();
    addTaskButton.setCaption("Add");
    addTaskButton.addClickListener(event -> onAddTask());

    taskGrid = new Grid<>();
    taskGrid.setCaption("Tasks");
    taskGrid.setWidth("100%");
    taskGrid.addColumn(Task::getId)
            .setCaption("#")
            .setExpandRatio(0)
            .setResizable(false);
    taskGrid.addColumn(Task::getText)
            .setCaption("Text")
            .setExpandRatio(1)
            .setEditorComponent(new TextField(), Task::setText);
    taskGrid.addColumn(task -> new Button(VaadinIcons.TRASH, event -> onDeleteTask(task)), new ComponentRenderer())
            .setCaption("Delete")
            .setExpandRatio(0)
            .setResizable(false)
            .setSortable(false);
    taskGrid.getEditor().setEnabled(true);

    sublayout = new HorizontalLayout();
    sublayout.setMargin(false);
    sublayout.setWidth("100%");
    sublayout.addComponent(newTaskField);
    sublayout.addComponent(addTaskButton);
    sublayout.setExpandRatio(newTaskField, 1);

    layout = new VerticalLayout();
    layout.addComponent(headerLabel);
    layout.addComponent(sublayout);
    layout.addComponent(taskGrid);

    setCompositionRoot(layout);
  }

  /**
   * Sets what tasks will be shown in the task list.
   */
  public void setTasks(Collection<Task> tasks) {
    taskGrid.setItems(tasks);
  }

  /**
   * Enables the presenter to get notified on "Add task" button click.
   */
  public void setAddTaskHandler(Consumer<String> handler) {
    addTaskHandler = handler;
  }

  /**
   * Enables the presenter to get notified on "Delete task" button click.
   */
  public void setDeleteTaskHandler(Consumer<Task> handler) {
    deleteTaskHandler = handler;
  }

  public void refreshData() {
    taskGrid.getDataProvider().refreshAll();
  }

  public void clearField() {
    newTaskField.clear();
  }

  public void showNotification(String text) {
    Notification notification = new Notification(text, Notification.Type.TRAY_NOTIFICATION);
    notification.setPosition(Position.TOP_CENTER);
    notification.show(this.getUI().getPage());
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
