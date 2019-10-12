package br.com.waldson.trabalho;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class Compressor {

  private Node raiz;

  public Compressor(MinHeap min) {
    this.raiz = MakeTree(min);
  }

  private Node MakeTree(MinHeap min) {

    if (min.getSize() == 1)
      return min.peek();

    Node n1 = min.peek();
    min.remove();

    Node n2 = min.peek();
    min.remove();

    Node sum = new Node(null, n1.getCount()+n2.getCount());
    sum.setLeft(n1);
    sum.setRight(n2);

    min.addNode(sum);

    return MakeTree(min);
  }

  public void print() {
    this.raiz.print(this.raiz, "");
  }

  public HashMap<Character, String> getMapCodes(){
    return raiz.getMapCodes();
  }


}
