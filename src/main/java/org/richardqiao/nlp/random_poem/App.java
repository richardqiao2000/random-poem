package org.richardqiao.nlp.random_poem;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.richardqiao.nlp.random_poem.rules.*;

public class App {
    public static void main( String[] args ) throws FileNotFoundException, IOException{
      Rule poem = new Rule("POEM");
      System.out.print(poem.randomGen());
    }
}
