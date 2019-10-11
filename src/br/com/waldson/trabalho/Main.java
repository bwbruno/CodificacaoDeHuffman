package br.com.waldson.trabalho;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        String command = args[0];
        String filename = args[1];

        System.out.println("Lendo arquivo: " + filename);

        if (command == "compress") {

        } else if (command  == "extract") {

        } else {
            System.out.println("Comando inválido");
        }


        FileWriter writer = null;

        try {
            writer = new FileWriter(args[0]);
            writer.write("Hello");
            writer.close();
            System.out.println(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MinHeap min = new MinHeap();

        HashMap<Character, Integer> dictionary = StringFrequency.evaluate("lollapalooza");
        for (Character character: dictionary.keySet())
            min.addNode(character, dictionary.get(character));

        min.printNodes();

        MinHeap exemplo = new MinHeap();
        exemplo.addNode('p', 1);
        exemplo.addNode('z', 1);
        exemplo.addNode('a', 3);
        exemplo.addNode('l', 4);
        exemplo.addNode('o', 3);

        System.out.println("_---------------------");

        Code code = new Code(min);
        code.print();


      HashMap<Character, String> mapCodes = code.getMapCodes();
      for (Character character: mapCodes.keySet())
        System.out.println(character.toString() + " " + mapCodes.get(character));
    }
}
