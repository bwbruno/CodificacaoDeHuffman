package br.com.waldson.trabalho;

import java.util.BitSet;
import java.util.HashMap;

public class StringHelper {

  public static HashMap<Character, Integer> mapFrequency(String text) {

    HashMap<Character, Integer> dictionary = new HashMap<>();

    for (int i = 0; i < text.length(); i++) {
      char character = new Character(text.charAt(i));

      Integer counter = dictionary.get(character);

      if(counter != null)
        dictionary.put(character, counter + 1);
      else
        dictionary.put(character, 1);
    }

    return dictionary;
  }

  public static String compress(String text, HashMap<Character, String> table) {

    String code = new String();
    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      code += table.get(c);
      System.out.println("code : " + code);
    }

    return code;
  }
  
  public static void print(String text) {
    
    HashMap<Character, Integer> dictionary = mapFrequency(text);

    for (Character character: dictionary.keySet()) {
      String key = character.toString();
      String value = dictionary.get(character).toString();

      System.out.println(key + ": " + value);
    }
  }

  // "111111"
  public static BitSet stringToBitSet(String text) {

    BitSet bits = new BitSet();

    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) == '0')
        bits.set(i, false);

      if (text.charAt(i) == '1')
        bits.set(i, true);
    }


    return bits;
  }

}
