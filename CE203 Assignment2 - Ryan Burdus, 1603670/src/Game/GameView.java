package Game;

/**
 * Created by Ryan on 29/11/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import static java.awt.Color.*;


public class GameView extends JComponent implements ActionListener {
    static Color[] colors = {gray, white, black, darkGray, red, green};//a list of colors that will be used in the game
    int[][] a;//an array for the game
    int w, h;//the width and the height
    static int size = 25;//The size of each array space
    private Random random;//creates a use for 'Random'
    private int dots;//creates a place to store the number of body parts
    private int totalArea = 400;//the number of total array spaces
    private int foodX;//creates the x-axis for food
    private int foodY;//creates th y-axis for food
    private final int x[] = new int[totalArea];//creates the x-axis for the snake
    private final int y[] = new int[totalArea];//creates the y-axis for the snake

    //These next 4 lines create the directions for the player/snake
    private boolean up= false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    private boolean gameRunning = true;//shows that the game is running
    private int timeDelay = 120;//sets the timer
    private Timer timer;//names the timer
    int score = 0;//sets the score



    public GameView(int[][] a)
    {
        //these lines allow for a keyListener to work
        addKeyListener(new PressingKeys());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        //these lines get the width and height of the array
        this.a = a;
        w = a.length;
        h = a[0].length;

        startGame();//this starts the game
    }



    private void startGame()
    {
        dots = 3;//sets the amount of parts for the snake
        score = 0;//sets the score to 0

        //draws in the snake
        for (int i = 0; i < dots; i++)
        {
            x[i] = 10 - i;
            y[i] = 10;
        }

        findFood();//draws in the food items

        //these lines start the timer for the game
        timer = new Timer(timeDelay, this);
        timer.start();
    }



    private void findFood()
    {
        random = new Random();//gets a new random instance
        foodX = random.nextInt(19) + 1;//finds a new random number
        foodY = random.nextInt(19) + 1;//finds a new random number

        for(int i = 0; i < dots; i++)//checks each snake body/head part
        {
            while((foodX == x[i]) && (foodX == y[i]))//if the body/head of the snake is in the same place as food, it changes the food position
            {
                foodX = random.nextInt(19) + 1;
                foodY = random.nextInt(19) + 1;
            }
        }
    }

    //

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //if the game is running, certain things happen. if not, the array is repainted
        if (gameRunning)
        {
            checkFood();//checks the foods location
            collisionCheck();//checks for the snakes collision
            move();//listens to the players move
        }
        repaint();
    }



    private void checkFood()
    {
        //checks if the snake head and the food are in the same position
        if ((x[0] == foodX) && (y[0] == foodY))
        {
            score += 10;//the score goes up by 10
            dots++;//the snake gains a body part
            findFood();//the next bit of food is placed
        }
    }



    private void collisionCheck()
    {
        //This for loop checks if the snake collides with itself
        for(int i = dots; i > 0; i--)
        {
            if((i > 4) && (x[0] == x[i]) && (y[0] == y[i]))
            {
                gameRunning = false;
            }
        }

        //These next 4 if statements check if the snake has hit the border of the game/array space
        if(y[0] >= h)
        {
            gameRunning = false;
        }
        //
        if(y[0] < 0)
        {
            gameRunning = false;
        }
        //
        if(x[0] >= w)
        {
            gameRunning = false;
        }
        //
        if(x[0] < 0)
        {
            gameRunning = false;
        }

        //This final if statement stops the timer if any collisions have happened
        if(!gameRunning)
        {
            timer.stop();
        }
    }



    private void move()
    {
        //this makes the snakes body follow the moves of the head
        for(int i = dots; i > 0; i--)
        {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if(left)//moves the head left
        {
            x[0] -= 1;
        }
        //
        if(right)//moves the head right
        {
            x[0] += 1;
        }
        //
        if(up)//moves the head up
        {
            y[0] -= 1;
        }
        //
        if(down)//moves the head down
        {
            y[0] += 1;
        }
    }



    private class PressingKeys extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int movementKey = e.getKeyCode();//gets the name of the key pressed

            if((movementKey == KeyEvent.VK_UP) && (!down))//sets the move to up if the up key is pressed
            {
                up = true;
                left = false;
                right = false;

            }
            //
            if((movementKey == KeyEvent.VK_DOWN) && (!up))//sets the move to down if the down key is pressed
            {
                down = true;
                left = false;
                right = false;
            }
            //
            if((movementKey == KeyEvent.VK_LEFT) && (!right))//sets the move to left if the left key is pressed
            {
                left = true;
                up = false;
                down = false;
            }
            //
            if((movementKey == KeyEvent.VK_RIGHT) && (!left))//sets the move to right if the right key is pressed
            {
                right = true;
                up = false;
                down = false;
            }
        }
    }



    private class MouseButtons extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {

        }

    }



    public void paintComponent(Graphics g)
    {
        //this paints the background of the game
        for (int i = 0; i < w; i++)
        {
            for (int j = 0; j < h; j++)
            {
                g.setColor(colors[1]);
                g.fill3DRect(i * size, j * size, size, size, true);
            }
        }

        drawItems(g);//draws in all of the items that the game includes
    }



    private void drawItems(Graphics g)
    {
        //if the game is running, the snake and food is being drawn
        if(gameRunning)
        {
            drawFood(g);//this calls to the drawFood() method to draw the food

            //this loop draws the snake
            for(int i = 0; i < dots; i++)
            {
                //this if statement draws the head
                if(i == 0)
                {
                    g.setColor(colors[0]);
                    g.fillRect(x[i] * size, y[i] * size, size, size);
                }
                //this part draws the body/tail
                else
                {
                    g.setColor(colors[5]);
                    g.fillRect(x[i] * size, y[i] * size, size, size);
                }
            }
        }
        else
        {
            gameOver(g);//calls to the gameOver(g) method when the game is over
        }
    }



    private void drawFood(Graphics g)
    {
        //goes through the array up until where the food will be drawn in and draws the food
        for(int i = 0; i < foodX; i++)
        {
            for(int j = 0; j < foodY; j++)
            {
                g.setColor(colors[4]);
                g.fill3DRect(foodX * size, foodY * size, size, size, true);
            }
        }
    }



    private void gameOver(Graphics g)
    {
        //this tries to call the writeToFile() method
        try
        {
            writeToFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //this tries to open the high-scores page
        try
        {
            new ScoresFrame("CE203 Assignment2: Ryan Burdus, 1603670");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    private void writeToFile() throws IOException
    {
        //tries to open and write to the high-scores file
        try(FileWriter fw = new FileWriter("All_high_scores.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(score);
        }
    }



    //sets the screen size
    public Dimension getPreferredSize()
    {
        return new Dimension(w * size, h * size);
    }
}
