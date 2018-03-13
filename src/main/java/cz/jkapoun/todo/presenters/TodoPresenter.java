package cz.jkapoun.todo.presenters;

import cz.jkapoun.todo.model.Task;
import com.vaadin.annotations.Theme;
import cz.jkapoun.todo.services.TaskService;
import cz.jkapoun.todo.views.TodoView;
import java.util.Collection;

@Theme("todo")
public class TodoPresenter {

  protected TaskService taskService;
  protected TodoView    todoView;

  public TodoPresenter(TaskService taskService, TodoView todoView) {
    this.taskService = taskService;
    this.todoView    = todoView;

    todoView.setAddTaskHandler(this::handleAddTask);
    todoView.setDeleteTaskHandler(this::handleDeleteTask);

    Collection<Task> tasks = taskService.getTasks();
    todoView.setTasks(tasks);
  }

  protected void handleAddTask(String text) {
    taskService.addTask(text);
    todoView.afterTaskAdded();
  }

  protected void handleDeleteTask(Task task) {
    int id = task.getId();
    taskService.deleteTask(id);
    todoView.afterTaskDeleted();
  }
}
