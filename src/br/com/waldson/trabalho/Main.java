package br.com.waldson.trabalho;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

      String command = args[0];
      String filename = args[1];
      String compressedFileName = args[2];

      System.out.println("Lendo arquivo: " + filename);

      if (command.equals("compress")) {
        Compressor compressor = new Compressor(filename, compressedFileName);

      } else if (command.equals("extract")) {

      } else {
          System.out.println("Comando inválido");
      }

      InputStream is = new FileInputStream(compressedFileName);
      long fileSize = new File(filename).length();
      byte[] allBytes = new byte[(int) fileSize];
      is.read(allBytes);

      BitSet bits = BitSet.valueOf(allBytes);

      for (int i = 0; i < bits.length(); i++) {
        System.out.println("isBits[" + i + "]: " + bits.get(i));
      }




    }

}
