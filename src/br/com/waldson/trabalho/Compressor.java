package br.com.waldson.trabalho;

import java.io.*;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

import static java.nio.file.Files.*;

public class Compressor {

  private Node raiz;
  private String txt;
  private String edz;
  private String edt;

  HashMap<Character, String> codeTable;

  public Compressor(String txt, String edz, String edt) throws IOException {
    this.txt = txt;
    this.edz = edz;
    this.edt = edt;

    String text = this.getText();
    HashMap<Character, Integer> letterFrequencyTable = StringHelper.letterFrequencyOf(text);
    MinHeap min = new MinHeap();
    min = createMinHeap(letterFrequencyTable);

    this.raiz = createTree(min);
    this.codeTable = getCodeTable();

    writeFile();
    writeDecodeTableFile();

    System.out.println("O arquivo foi possui " + getCompressionRatio() + "% do seu tamanho original");
  }

  public String getText() throws IOException {
    return new String(readAllBytes(Paths.get(txt)));
  }

  private MinHeap createMinHeap(HashMap<Character, Integer> letterFrequencyTable) {
    MinHeap min = new MinHeap();

    for (Character character: letterFrequencyTable.keySet())
      min.addNode(character, letterFrequencyTable.get(character));

    return min;
  }


  private Node createTree(MinHeap min) {

    if (min.getSize() == 0)
      return new Node(null, 1);

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

  public HashMap<Character, String> getCodeTable(){
    return raiz.getCodeTable();
  }

  private void writeFile() throws IOException {
    BitSet bits = getBitEncodedText();
    FileOutputStream os = new FileOutputStream(this.edz);
    os.write(bits.toByteArray());
    os.close();
  }

  public BitSet getBitEncodedText() throws IOException {
    String codedText = this.encodeText();
    return StringHelper.stringToBitSet(codedText);
  }

  public String encodeText() throws IOException {
    String text = this.getText();
    HashMap<Character, String> table = this.getCodeTable();

    String codedText = new String();
    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      codedText += table.get(c);
    }
/*
    // Imprime o código de 8 em 8
    for (int i = 0; i < codedText.length(); i++) {
      if (i % 8 == 0)
        System.out.print(" ");
      System.out.print(codedText.charAt(i));
    }
    System.out.println("\nfim encode()");
*/
    return codedText;
  }

  private void writeDecodeTableFile() throws IOException {
    FileWriter writer = null;

    try {
      writer = new FileWriter(edt);

      for (Character character: this.codeTable.keySet())
        writer.write(character + this.codeTable.get(character) + "\n");

      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public float getCompressionRatio() {
    File file = new File(this.txt);
    File compressed = new File(this.edz);

    return (compressed.length() * 100) / (file.length() == 0 ? 1 : file.length()) ;
  }

  public void print() {
    this.raiz.print(this.raiz, "");
  }

}
