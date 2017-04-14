# Poem Generator

## Classses Design
* Rule -- A set of inter-reference rule objects, with a recursive method randomGen which is used to generate poems
* Poem -- Poem class with a list of LINE rule

## How to run
```Java
  Poem poem = new Poem();
  System.out.println(poem.poemGen());
```