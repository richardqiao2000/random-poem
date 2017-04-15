# Poem Generator(upgraded version with single class Rule)
*The new version makes the code more generic and extendable. For example, we can change the rule in the original rule POEM*

*from "POEM=<LINE> <LINE> <LINE> <LINE> <LINE>"*

*to "POEM=<LINE> <LINE> <LINE> <LINE> <LINE> <PREPOSITION>|<ADJECTIVE>"*

*Or*

*NOUN=heart|sun|moon|thunder|fire|time|wind|sea <VERB>|<PREPOSITION> river|flavor|wave|willow|rain|tree|flower|field|meadow|pasture|harvest|water|father|mother|brother|sister $END*

## Classs Design
In package "org.richardqiao.nlp.random_poem.rules"
* Class Rule is for all the rules.
* When initiated, a set of inter-reference different rule objects will be built and referred to each other, with a recursive method randomGen which is used to generate poems.
* A Rule could be a pure word list rule or Mixed. The definition is recursive.
* It's using a static HashMap to maintain the single object to be used repeatly.
```Java
  //Flag if it's a pure word list rule
  private boolean isWordList = false;
  //words used to record the starting words
  private String[] words;
  //rules is saving the rules sequentially after begin words
  private List<Rule[]> rules = new ArrayList<Rule[]>();

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
```

## How to run
```Java
    Rule poem = new Rule("POEM");
    System.out.println(poem.randomGen());
```
Outputs:
```
moon stands his river against black 
willow flows my bright clear wind 
flavor beside your black muddy harvest above flavor outside time 
my white 
my bright pasture 
```
```
river upon thunder flies 
between willow 
willow underneath clear heart 
behind heart 
his flavor upon his flavor stands my clear murky 
```
```
my muddy sister 
beside heart before clear willow transcends against my father transcends with field between pasture flows against tree around river sinks your brother around light black light 
above white father flies behind moon flies 
your flower along harvest crawls 
my time underneath her water beneath bright meadow with her black muddy light 
```