package br.com.waldson.trabalho;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

      String command = args[0];
      System.out.println("Lendo arquivo: " + args[1]);

      if (command.equals("compress")) {
        String txt = args[1];
        String edz = args[2];
        String edt = args[3];
        Compressor compressor = new Compressor(txt, edz, edt);

      } else if (command.equals("extract")) {
        String edz = args[1];
        String edt = args[2];
        String txt = args[3];
        Extractor extractor = new Extractor(edz, edt, txt);

      } else {
          System.out.println("Comando inválido");
      }

    }

}
