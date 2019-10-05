# Java-snake-game---University-2nd-year-project
### Created by Ryan Burdus, November 2017
A classic Snake game made in Java when at University.
This game is a recreation of the classic game "Snake" and was **made in IntelliJ with Java** where the main features of the game are: 
- Working singleplayer snake game
- Scores connected to a text file which can be displayed in their own menu

The player will be able to play a normal game of Snake and when the player finishes their playthrough they will then be able to see the socres table and will then be able to choose whether to play again or to close the game and end the program. 

**All code used within this game has been created by myself (Ryan Burdus) and nobody else.**

## Code Highlights

- Food placer 
```Java
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
```

- Writing to a file
```Java
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
```

- Getting scores from file into JFrame
```Java
//This section of code adds the title and other needed text to the text area
        scoresArea1.append("All time top 10 High-scores:\n\n");
        scoresArea1.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        scoresArea1.append("Rankings     ||     High-scores\n");
        scoresArea1.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        int rankingNumber = 1;//this sets the ranking number

        Collections.sort(numbers);//sorts the numbers, lowest to highest
        Collections.reverse(numbers);//reverses the numbers to highest to lowest
        //
        //this if statement checks if there are less than 10 scores and adds them to the textArea
        if(numbers.size() < 10)
        {
            for(int i = 0; i < numbers.size(); i++)
            {
                scoresArea1.append(String.valueOf(rankingNumber + "     ||     " + numbers.get(i) + "\n"));
                rankingNumber++;
            }
        }
        //if there are at least 10, the score are added to the textArea a different way
        else
        {
            for(int i = 0; i < 10; i++)
            {
                if(i < 9)
                {
                    scoresArea1.append(String.valueOf(rankingNumber + "     ||     " + numbers.get(i) + "\n"));
                    rankingNumber++;
                }
                else
                {
                    scoresArea1.append(String.valueOf(rankingNumber + "   ||     " + numbers.get(i) + "\n"));
                    rankingNumber++;
                }
            }
        }
        scoresArea1.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
```

## How to run the game 
 - To run the program you will need to have IntelliJ 2018.1.6 installed on your computer and to load up the project once this is finished.

- To run the game as a build you will need to run the .jar that is found within this project folder.

## Screenshots

- Gameplay screen

![alt text](https://github.com/Ryan-Paul-Burdus/Java-snake-game---University-2nd-year-project/blob/master/Screenshots/SnakeGameplay.png "Gameplay screen")

- ScoreBoard screen

![alt text](https://github.com/Ryan-Paul-Burdus/Java-snake-game---University-2nd-year-project/blob/master/Screenshots/SnakeScores.png "Scoreboard screen")
