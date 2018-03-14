package cz.jkapoun.todo.services;

import com.vaadin.spring.annotation.VaadinSessionScope;
import cz.jkapoun.todo.model.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Repository of the Task objects.
 * Manages the creation of Task objects, stores them and provides them on
 * demand. Also ensures that IDs of the Tasks are unique.
 * By using @VaadinSessionScope annotation, TaskService objects are scoped to
 * the Vaadin session. This ensures the persistence of the Tasks on page reload.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 * @see    Task
 */
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

  /**
   * Returns a collection containing all the stored tasks.
   * Changes of the tasks (additions, edits, removals) are automatically
   * propagated to this collection.
   */
  public Collection<Task> getTasks() {
    return tasks.values();
  }
  
}
