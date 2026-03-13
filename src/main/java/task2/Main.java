package task2;

public class Main {
  public static void main(String[] args) {
    TestObj[] tests = { 
      new TestObj(1), 
      new TestObj(5),
      new TestObj(14),
      new TestObj(123),
      new TestObj(134),
      new TestObj(11),
      new TestObj(5),
    };

    SplayTree st = new SplayTree();

    System.out.println(st.isEmpty());

    for (TestObj test: tests) {
      st.insert(test);
      System.out.println(st.inOrderTraversal());
    }

    TestObj t5 = new TestObj(5);

    System.out.println("is 5? " + st.search(t5));

    System.out.println(st.inOrderTraversal());
    
    st.delete(null);
 
    System.out.println(st.inOrderTraversal());
 
    st.delete(t5);

    System.out.println(st.inOrderTraversal());

    System.out.println("is 5? " + st.search(t5));
  }
}

class TestObj implements Comparable<Object> {
  int a;

  TestObj(int a) {
    this.a = a;
  }

  public int compareTo(Object arg0) {
    return a - ((TestObj) arg0).a;
  }

  public String toString() {
    return String.valueOf(a);
  }
}