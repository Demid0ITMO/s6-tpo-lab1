package task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SplayTreeTest {

  SplayTree tree = new SplayTree();

    @BeforeEach
    public void setUp() {
      tree.clearLog();
    }

    private void assertLog(List<String> expected) {
      assertEquals(expected, tree.log);
    }

    @Test
    public void testInsertIntoEmptyTree() {
        tree.insert(new TestObj(5));
        assertLog(Arrays.asList("insert enter", "splay enter"));
    }

    @Test
    public void testInsertWithZig() {
        tree.insert(new TestObj(10));
        tree.insert(new TestObj(5));
        assertLog(Arrays.asList(
                "insert enter", "splay enter",
                "insert enter", "splay enter", "zig", "rotateRight enter"
        ));
    }

    @Test
    public void testSearchWithZigZigLeft() {
        tree.insert(new TestObj(1));
        tree.insert(new TestObj(2));
        tree.insert(new TestObj(3));
        tree.insert(new TestObj(4));
        tree.search(new TestObj(1));
        assertLog(Arrays.asList(
                "insert enter", "splay enter",
                "insert enter", "splay enter", "zig", "rotateLeft enter",
                "insert enter", "splay enter", "zig", "rotateLeft enter",
                "insert enter", "splay enter", "zig", "rotateLeft enter",
                "search enter", "splay enter", "zig zig left",
                "rotateRight enter", "rotateRight enter", "zig", "rotateRight enter"
        ));
    }

    @Test
    public void testInsertWithZigZagRightLeft() {
        tree.insert(new TestObj(5));
        tree.insert(new TestObj(3));
        tree.insert(new TestObj(4));
        assertLog(Arrays.asList(
                "insert enter", "splay enter",
                "insert enter", "splay enter", "zig", "rotateRight enter",
                "insert enter", "splay enter", "zig zag right left",
                "rotateRight enter", "rotateLeft enter"
        ));
    }

    @Test
    public void testDeleteRoot() {
        tree.insert(new TestObj(5));
        tree.insert(new TestObj(3));
        tree.insert(new TestObj(4));
        tree.clearLog();
        tree.delete(new TestObj(4));
        assertLog(Arrays.asList(
                "delete enter", "splay enter", "splay enter"
        ));
    }

    @Test
    public void testInsertDuplicate() {
        tree.insert(new TestObj(5));
        tree.insert(new TestObj(3));
        tree.insert(new TestObj(4));
        tree.clearLog();
        tree.insert(new TestObj(4));
        assertLog(Arrays.asList(
                "insert enter", "insert duplicate", "splay enter"
        ));
    }

    @Test
    public void testSearchNonExisting() {
        tree.insert(new TestObj(5));
        tree.insert(new TestObj(3));
        tree.clearLog();
        tree.search(new TestObj(10));
        assertLog(Arrays.asList("search enter"));
    }
}