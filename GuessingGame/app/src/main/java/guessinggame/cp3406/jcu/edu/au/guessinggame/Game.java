package guessinggame.cp3406.jcu.edu.au.guessinggame;

import java.util.Random;

public class Game {
    Random randnum = new Random();
    int num = (randnum.nextInt(10) +1);

    public boolean check(int i) {
        return(num == i);
    }

}
