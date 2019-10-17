package br.com.waldson.trabalho;

import java.io.*;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

import static java.nio.file.Files.readAllBytes;

public class Extractor {

  private String edz;
  private String edt;
  private String txt;
  private HashMap<String, Character> decodedTable;
  private String compressedText;
  private String decodedText = "";

  public Extractor(String edz, String edt, String txt) throws IOException {
    this.edz = edz;
    this.edt = edt;
    this.txt = txt;

    this.decodedTable = readDecodedTableFile();
    readCompressedFile();
    decodeText();
    writeFile();
  }

  private void writeFile() throws IOException {
    FileWriter writer = null;
    
    try {
      writer = new FileWriter(this.txt);
      writer.write(this.decodedText);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    checkLastByte();
  }

  private void checkLastByte() throws IOException {

    File file = new File(txt);
    RandomAccessFile raf = new RandomAccessFile(file, "r");

    byte b[] = readAllBytes(Paths.get(txt));
    raf.seek(file.length() - 1);
    raf.read(b, 0, 1);
    BitSet bit = new BitSet(8);
    bit = BitSet.valueOf(b);

    System.out.println("Bytes:: " + StringHelper.bitSetToString(bit));




  }

  private void decodeText() {
    String bits = "";

    for (int i = 0; i < compressedText.length(); i++) {

      bits += compressedText.charAt(i);

      if (decodedTable.get(bits) != null) {
        this.decodedText += decodedTable.get(bits);
        bits = "";
      }

    }
  }


  private void readCompressedFile() throws IOException {

    InputStream is = new FileInputStream(edz);
    long fileSize = new File(edz).length();
    byte[] allBytes = new byte[(int) fileSize];
    is.read(allBytes);

    BitSet bits = BitSet.valueOf(allBytes);
/*
    for (int i = 0; i < bits.length(); i++) {
      System.out.println("isBits[" + i + "]: " + bits.get(i));
    }
*/
    this.compressedText = StringHelper.bitSetToString(bits);
  }


  private HashMap<String, Character> readDecodedTableFile() throws IOException {
    FileReader fr = new FileReader(this.edt);

    HashMap<String, Character> table = new HashMap<>();
    String code = "";
    Character character;

    int i = 0;
    while (i != -1) {

      i = fr.read();
      character = (char) i;

      i = fr.read();
      while (i == (int) '0' || i == (int) '1') {
        code += (char) i;
        i = fr.read();
      }

      table.put(code, character);
      code = "";

    }
/*
    for (String string: table.keySet())
     System.out.println(string + ": " + table.get(string));
*/
    return table;
  }

  public HashMap<String, Character> getDecodedTable() {
    return this.decodedTable;
  }


}
