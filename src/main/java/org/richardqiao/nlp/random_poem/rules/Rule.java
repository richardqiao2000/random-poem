package org.richardqiao.nlp.random_poem.rules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class Rule {
  private static Map<String, Rule> map;
  private boolean isLine = false;
  protected Rule[] rules;
  protected String[] beginWords;
  protected static Properties props;
  
  public Rule(String ruleName) throws FileNotFoundException, IOException{
    if(map == null){
      map = new HashMap<String, Rule>();
    }
    if(props == null){
      props = new Properties();
      props.load(new FileInputStream("src/main/resources/rules.properties"));
    }
    if(ruleName.equals("$END") || ruleName.equals("$LINEBREAK")){
      return;
    }
    if(ruleName.equals("LINE")) isLine = true;
    String rule = props.getProperty(ruleName).replace("<", "").replace(">", "");
    String[] ruleList = new String[0];
    if(ruleName.equals("POEM")){
      ruleList = rule.split(" ");
    }else if(ruleName.equals("LINE")){
      ruleList = rule.split(" ")[0].split("\\|");
    }else{
      String[] arr = rule.split(" ");
      beginWords = arr[0].split("\\|");
      ruleList = arr[1].split("\\|");
    }
    rules = new Rule[ruleList.length];
    map.put(ruleName, this);
    for(int i = 0; i < rules.length; i++){
      if(map.containsKey(ruleList[i])){
        rules[i] = map.get(ruleList[i]);
      }else{
        rules[i] = new Rule(ruleList[i]);
      }
    }
  }


  private String getRandomBeginWord() {
    if(beginWords == null || beginWords.length == 0)return "";
    return beginWords[new Random().nextInt(beginWords.length)];
  }
  
  private Rule getRandomRule(){
    return rules[new Random().nextInt(rules.length)];
  }

  public String randomGen(){
    if(rules == null || rules.length == 0){
      return "";
    }
    StringBuilder sb = new StringBuilder();
    String begin = getRandomBeginWord();
    if(begin.length() > 0){
      sb.append(begin + " ");
    }
    sb.append(getRandomRule().randomGen());
    if(isLine) sb.append('\n');
    return sb.toString();
  }

}
