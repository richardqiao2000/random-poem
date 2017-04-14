package org.richardqiao.nlp.random_poem.rules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Poem {
  private Rule[] rules;
  public Poem() throws FileNotFoundException, IOException{
    Properties props = new Properties();
    props.load(new FileInputStream("src/main/resources/rules.properties"));
    String[] arr = props.getProperty("POEM").replace("<", "")
                          .replace(">", "").split(" ");
    rules = new Rule[arr.length];
    for(int i = 0; i < rules.length; i++){
      if(Rule.getMap().isEmpty()){
        rules[i] = new Rule(arr[i].trim());
      }else{
        rules[i] = Rule.getMap().get(arr[i].trim());
      }
    }
  }
  
  public String poemGen(){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < rules.length; i++){
      String tmp = rules[i].randomGen();
      sb.append(tmp);
    }
    return sb.toString();
  }
}
