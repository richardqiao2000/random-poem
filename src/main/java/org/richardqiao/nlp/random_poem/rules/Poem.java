package org.richardqiao.nlp.random_poem.rules;

import java.io.*;

public class Poem {
  private Rule[] rules;
  public Poem() throws FileNotFoundException, IOException{
    Rule.setProperties("src/main/resources/rules.properties");
    String[] arr = Rule.getProperties().getProperty("POEM").replace("<", "")
                          .replace(">", "").split(" ");
    rules = new Rule[arr.length];
    for(int i = 0; i < rules.length; i++){
      if(Rule.getMap().isEmpty() || !Rule.getMap().containsKey(arr[i].trim())){
        rules[i] = new Rule(arr[i].trim());
      }else{
        rules[i] = Rule.getMap().get(arr[i].trim());
      }
    }
  }
  
  public String poemGen(){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < rules.length; i++){
      sb.append(rules[i].randomGen());
    }
    return sb.toString();
  }
}
