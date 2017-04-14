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
    int size = props.getProperty("POEM").split(" ").length;
    Rule rule = new Rule("LINE");
    rules = new Rule[size];
    for(int i = 0; i < rules.length; i++){
      rules[i] = rule;
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
