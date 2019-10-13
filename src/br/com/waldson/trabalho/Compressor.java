package br.com.waldson.trabalho;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

import static java.nio.file.Files.*;

public class Compressor {

  private Node raiz;

  public Compressor(String filename, String compressedFileName) throws IOException {

    String text = new String(readAllBytes(Paths.get(filename)));
    System.out.println("filename" + filename);

    MinHeap min = new MinHeap();
    min = createMinHeap(StringHelper.letterFrequencyOf(text));
    this.raiz = createTree(min);

    String codedText = encode(text, getCodeTable());
    BitSet bits = StringHelper.stringToBitSet(codedText);
    writeFile(compressedFileName, bits);

    System.out.println(text);
  }

  private void writeFile(String filename, BitSet bits) throws IOException {
    FileOutputStream os = new FileOutputStream(filename);
    os.write(bits.toByteArray());
    os.close();
  }

  public Compressor(MinHeap min) {
    this.raiz = createTree(min);
  }

  private MinHeap createMinHeap(HashMap<Character, Integer> letterFrequencyTable) {

    MinHeap min = new MinHeap();

    for (Character character: letterFrequencyTable.keySet())
      min.addNode(character, letterFrequencyTable.get(character));

    min.printNodes();

    return min;
  }

  private Node createTree(MinHeap min) {

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

    return createTree(min);
  }

  public void print() {
    this.raiz.print(this.raiz, "");
  }

  public HashMap<Character, String> getCodeTable(){
    return raiz.getCodeTable();
  }

  public static String encode(String text, HashMap<Character, String> table) {

    String codedText = new String();
    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      codedText += table.get(c);
    }

    // Imprime o código de 8 em 8
    for (int i = 0; i < codedText.length(); i++) {
      if (i % 8 == 0)
        System.out.print(" ");
      System.out.print(codedText.charAt(i));
    }
    System.out.println("\nfim encode()");

    return codedText;
  }

}
