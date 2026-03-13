package task3.item;

public abstract class Item {
  private String name;

  Item(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}