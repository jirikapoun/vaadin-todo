package cz.jkapoun.todo.model;

public class Task {

  protected int    id;
  protected String text;

  public Task(int id, String text) {
    this.id   = id;
    this.text = text;
  }

  public int getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
