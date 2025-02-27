package br.com.waldson.trabalho;

import java.util.HashMap;

public class Node {
  private Character letter;
  private int count;
  private Node left;
  private Node right;

  public Node(Character letter, int count) {
    this.letter = letter;
    this.count = count;
  }

  public int getCount() {
    return count;
  }

  public char getLetter() {
    return letter;
  }

  void print(Node node, String prefix) {
    if(node == null) return;
    System.out.println(prefix + " |-- " + node.letter + "(" + node.count + ")");
    print(node.left , prefix + "   ");
    print(node.right , prefix + "   ");
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public HashMap<Character, String> getCodeTable() {
    HashMap<Character, String> mapCodes = new HashMap<>();
    getCodeTable("", mapCodes);
    return mapCodes;
  }

  public void getCodeTable(String code, HashMap<Character, String> codesTable) {

    if (this.letter != null) {
      codesTable.put(this.letter, code);
    }

    if (this.left != null) {

      this.left.getCodeTable(code + '0', codesTable);
    }

    if (this.right != null) {
      this.right.getCodeTable(code + '1', codesTable);
    }

  }

  public void accessPreOrder(NodeVisitor v, String code,  HashMap<Character, String> mapCodes) {
    v.visit(this);

    if (this.left != null) {

      this.left.accessPreOrder(v, code + '0', mapCodes);
    }

    if (this.right != null) {
      this.right.accessPreOrder(v, code + '1', mapCodes);
    }
  }

  public boolean isLeaf() {
    return (left == null && right == null);
  }

}
