package task2;

import java.util.ArrayList;

public class SplayTree {
  
  public ArrayList<String> log = new ArrayList<>();

  private static class Node {
    Comparable<Object> data;
    Node left;
    Node right;
    Node parent;

    Node(Comparable<Object> data) {
      this.data = data;
      this.left = null;
      this.right = null;
      this.parent = null;
    }
  }

  private Node root;

  public SplayTree() {
    this.root = null;
  }

  public void clearLog() {
    log.clear();
  }

  private void rotateLeft(Node x) {
    log.add("rotateLeft enter");
    Node y = x.right;
    x.right = y.left;
    
    if (y.left != null) y.left.parent = x;
    
    y.parent = x.parent;
    
    if (x.parent == null) root = y;
    else if (x == x.parent.left) x.parent.left = y;
    else x.parent.right = y;
    
    y.left = x;
    x.parent = y;
  }

  private void rotateRight(Node x) {
    log.add("rotateRight enter");
    Node y = x.left;
    x.left = y.right;

    if (y.right != null) y.right.parent = x;
    
    y.parent = x.parent;

    if (x.parent == null) root = y;
    else if (x == x.parent.right) x.parent.right = y;
    else x.parent.left = y;

    y.right = x;
    x.parent = y;
  }

  private void splay(Node x) {
    log.add("splay enter");
    if (x == null) return;

    while (x.parent != null) {
      Node parent = x.parent;
      Node grandpa = parent.parent;

      if (grandpa == null) {
        log.add("zig");
        if (x == parent.left) rotateRight(parent);
        else rotateLeft(parent);
      } else {
        if (x == parent.left && parent == grandpa.left) {
          log.add("zig zig left");
          rotateRight(grandpa);
          rotateRight(parent);
        } else if (x == parent.right && parent == grandpa.right) {
          log.add("zig zig right");
          rotateLeft(grandpa);
          rotateLeft(parent);
        } else if (x == parent.right && parent == grandpa.left) {
          log.add("zig zag left right");
          rotateLeft(parent);
          rotateRight(grandpa);
        } else if (x == parent.left && parent == grandpa.right) {
          log.add("zig zag right left");
          rotateRight(parent);
          rotateLeft(grandpa);
        }
      }
    }
    root = x;
  }

  public void insert(Comparable<Object> data) {
    log.add("insert enter");
    if (data == null) return;

    Node newNode = new Node(data);
    Node current = root;
    Node parent = null;

    while (current != null) {
      parent = current;
      if (data.compareTo(current.data) < 0) current = current.left;
      else if (data.compareTo(current.data) > 0) current = current.right;
      else {
        log.add("insert duplicate");
        splay(current);
        return;
      }
    }

    newNode.parent = parent;
    if (parent == null) {
      root = newNode;
    } else if (data.compareTo(parent.data) < 0) {
      parent.left = newNode;
    } else {
      parent.right = newNode;
    }

    splay(newNode);
  }

  public boolean search(Comparable<Object> data) {
    log.add("search enter");
    if (data == null) return false;
    
    Node node = findNode(data);
    if (node != null) {
      splay(node);
      return true;
    } else {
      return false;
    }
  }

  private Node findNode(Comparable<Object> data) {
    Node current = root;
    while (current != null) {
      if (data.compareTo(current.data) == 0) return current;
      if (data.compareTo(current.data) < 0) current = current.left;
      else current = current.right;
    }
    return null;
  }

  public void delete(Comparable<Object> data) {
    log.add("delete enter");
    if (data == null) return;

    Node nodeToDelete = findNode(data);
    if (nodeToDelete == null) return; 

    splay(nodeToDelete);

    Node leftSubtree = root.left;
    Node rightSubtree = root.right;

    if (leftSubtree != null) leftSubtree.parent = null;
    if (rightSubtree != null) rightSubtree.parent = null;

    if (leftSubtree != null) {
      Node maxLeft = leftSubtree;
      while (maxLeft.right != null) maxLeft = maxLeft.right;
      
      splay(maxLeft);
      maxLeft.right = rightSubtree;
      
      if (rightSubtree != null) rightSubtree.parent = maxLeft;
      
      root = maxLeft;
    } else {
      root = rightSubtree;
    }
  }

  public String inOrderTraversal() {
    return inOrderHelper(root);
  }

  private String inOrderHelper(Node node) {
    String ans = "";
    if (node != null) ans = inOrderHelper(node.left) + node.data.toString() + " " + inOrderHelper(node.right);
    return ans;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public Comparable<Object> getRootData() {
    return root == null ? null : root.data;
  }
}