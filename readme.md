# Poem Generator

## Classses Design
In package "org.richardqiao.nlp.random_poem.rules"
* Rule -- Rule class for all the rules other than POEM.
  When initiated, a set of inter-reference different rule objects will be built and refered, with a recursive method randomGen which is used to generate poems
* Poem -- Poem class with a list of LINE rule

## How to run
```Java
  Poem poem = new Poem();
  System.out.println(poem.poemGen());
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