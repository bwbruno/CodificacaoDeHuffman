package br.com.waldson.trabalho;

import sun.security.util.math.intpoly.IntegerPolynomial;

import java.util.HashMap;

public class StringFrequency {

  public static HashMap<Character, Integer> evaluate(String text) {

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
  
  public static void print(String text) {
    
    HashMap<Character, Integer> dictionary = evaluate(text);

    for (Character character: dictionary.keySet()) {
      String key = character.toString();
      String value = dictionary.get(character).toString();

      System.out.println(key + ": " + value);
    }
  }

}
