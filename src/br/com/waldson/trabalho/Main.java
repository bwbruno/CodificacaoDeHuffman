package br.com.waldson.trabalho;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException {

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

        HashMap<Character, Integer> dictionary = StringHelper.mapFrequency("lollapalooza");
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

        Compressor compressor = new Compressor(min);
        compressor.print();


      HashMap<Character, String> mapCodes = compressor.getMapCodes();
      for (Character character: mapCodes.keySet())
        System.out.println(character.toString() + " " + mapCodes.get(character));

      String code = StringHelper.compress("lollapalooza", mapCodes);

        System.out.println("------" + code);

      BitSet bits = StringHelper.stringToBitSet(code);


        for (int i = 0; i < bits.length(); i++) {
            System.out.println("bits[" + i + "]: " + bits.get(i));
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(bits);
        oos.close();
        byte[] bytes = baos.toByteArray();
        System.out.println(bytes.length);


    }

}
