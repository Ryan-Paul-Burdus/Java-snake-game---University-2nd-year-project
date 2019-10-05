package Game;


/**
 * Created by Ryan on 29/11/2017.
 */

public class GameViewTest
{
    public static void main(String[] args)
    {
        int w = 20;//creates the width value
        int h = 20;//creates the height value
        int[][] a = new int[w][h];//creates a 2d array for the game to be played on
        GameView tv = new GameView(a);//creates a new instance of GameView()
        new GameFrame(tv, "CE203 Assignment2: Ryan Burdus, 1603670");//creates a new frame for GameFrame
    }
}
