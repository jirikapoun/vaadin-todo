package cz.jkapoun.todo;

public class Task {

  protected int    id;
  protected String text;

  protected static int nextId = 1;

  public Task(String text) {
    this.id   = nextId;
    this.text = text;
    nextId++;
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
