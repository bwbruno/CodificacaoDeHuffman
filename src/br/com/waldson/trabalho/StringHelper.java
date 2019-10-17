package br.com.waldson.trabalho;

import java.util.BitSet;
import java.util.HashMap;

public class StringHelper {

  public static HashMap<Character, Integer> letterFrequencyOf(String text) {

    HashMap<Character, Integer> dictionary = new HashMap<>();

    for (int i = 0; i < text.length(); i++) {
      char character = text.charAt(i);

      Integer counter = dictionary.get(character);

      if(counter != null)
        dictionary.put(character, counter + 1);
      else
        dictionary.put(character, 1);
    }

    return dictionary;
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

  public static String bitSetToString(BitSet bits) {
    String string = "";
    for (int i = 0; i < bits.length(); i++) {
      if (bits.get(i))
        string += "1";
      else
        string += "0";
    }

    return string;
  }



  public static String removeExtension(String filename) {
    if (filename != null && filename.lastIndexOf(".") > 0)
      return filename.substring(0, filename.lastIndexOf("."));
    else
      return filename;
  }


}
