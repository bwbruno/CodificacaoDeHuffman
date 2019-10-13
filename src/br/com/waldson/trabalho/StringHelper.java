package br.com.waldson.trabalho;

import java.util.BitSet;
import java.util.HashMap;

public class StringHelper {

  public static HashMap<Character, Integer> letterFrequencyOf(String text) {

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
    }

    return code;
  }
  
  public static void print(String text) {
    
    HashMap<Character, Integer> dictionary = letterFrequencyOf(text);

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

  public static void decodeTable() {

  }

  public static String removeExtension(String filename) {
    if (filename != null && filename.lastIndexOf(".") > 0)
      return filename.substring(0, filename.lastIndexOf("."));
    else
      return filename;
  }


}
