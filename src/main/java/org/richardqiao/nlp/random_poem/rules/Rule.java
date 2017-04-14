package org.richardqiao.nlp.random_poem.rules;
import java.io.*;
import java.util.*;

public class Rule {
  private static Map<String, Rule> map = new HashMap<String, Rule>();
  private static Properties props;
  private boolean isWordList = false;
  private String[] words;
  private List<Rule[]> rules = new ArrayList<Rule[]>();
  
  public Rule(){
    
  }
  
  public Rule(String[] words){
    isWordList = true;
    this.words = words;
  }
  
  public Rule(String ruleName) throws FileNotFoundException, IOException{
    if(props == null){
      props = new Properties();
      props.load(new FileInputStream("src/main/resources/rules.properties"));
    }
    String rule = props.getProperty(ruleName);
    if(rule == null){
      return;
    }
    String[] ruleList = rule.split(" ");
    map.put(ruleName, this);
    for(int i = 0; i < ruleList.length; i++){
      String tmp = ruleList[i].trim();
      if(tmp.startsWith("<") || tmp.startsWith("$")){
        tmp = tmp.replace("<", "").replace(">", "");
        rules.add(getRules(tmp));
      }else{
        rules.add(getWordRules(tmp));
      }
    }
  }

  public static Map<String, Rule> getMap(){
    return map;
  }
  
  public void setIsWordList(boolean iswordlist){
    isWordList = iswordlist;
  }
  
  private Rule[] getRules(String rule) throws FileNotFoundException, IOException{
    String[] arr = rule.split("\\|");
    Rule[] res = new Rule[arr.length];
    for(int i = 0; i < arr.length; i++){
      String tmp = arr[i].trim();
      if(map.containsKey(tmp)){
        res[i] = map.get(tmp);
      }else{
        Rule rl = null;
        if(tmp.equals("$END")){
          rl = new Rule();
        }else if(tmp.equals("$LINEBREAK")){
          rl = new Rule(new String[]{"\n"});
          rl.setIsWordList(true);
        }else{
          rl = new Rule(tmp);
        }
        res[i] = rl;
      }
    }
    return res;
  }
  
  private Rule[] getWordRules(String rule){
    Rule[] res = new Rule[1];
    res[0] = new Rule(rule.split("\\|"));
    return res;
  }
  
  private String getRandomWord() {
    return words[new Random().nextInt(words.length)];
  }
  
  private Rule getRandomRule(Rule[] rules){
    return rules[new Random().nextInt(rules.length)];
  }

  //Generate each rule's poem text recursively
  public String randomGen(){
    if(rules == null && words == null){
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for(Rule[] rs: rules){
      Rule rule = getRandomRule(rs);
      String tmp = "";
      if(rule.isWordList){
        tmp = rule.getRandomWord();
      }else{
        tmp = rule.randomGen();
      }
      if(tmp.trim().length() > 0
          && sb.length() > 0
          && sb.charAt(sb.length() - 1) != '\n'){
        sb.append(" ");
      }
      sb.append(tmp);
    }
    return sb.toString();
  }

}
