package cz.jkapoun.todo.services;

import com.vaadin.spring.annotation.VaadinSessionScope;
import cz.jkapoun.todo.model.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
@VaadinSessionScope
public class TaskService {

  protected Map<Integer, Task> tasks;
  protected int                nextId;

  public TaskService() {
    tasks  = new HashMap<>();
    nextId = 1;
  }

  public Task addTask(String text) {
    Task task = new Task(nextId, text);
    tasks.put(nextId, task);
    nextId++;
    return task;
  }

  public void deleteTask(int id) {
    tasks.remove(id);
  }

  public Task getTask(int id) {
    return tasks.get(id);
  }

  public Collection<Task> getTasks() {
    return tasks.values();
  }
  
}
