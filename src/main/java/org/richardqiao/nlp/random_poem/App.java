package org.richardqiao.nlp.random_poem;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.richardqiao.nlp.random_poem.rules.*;

public class App {
    public static void main( String[] args ) throws FileNotFoundException, IOException{
        //Poem poem = new Poem();
        //System.out.println(poem.poemGen());
      Rule poem = new Rule("POEM");
      System.out.println(poem.randomGen());
    }
}
