package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;


/**
 * Created by Ryan on 08/12/2017.
 */

public class ScoresFrame extends JFrame
{
    public ScoresFrame(String title) throws IOException {
        super(title);//uses the title from the GameViewTest class

        JPanel panel = new JPanel();//creates a new panel for the buttons
        //these  lines create the buttons
        JButton playAgain = new JButton("Play again");
        JButton exit = new JButton("Exit");

        //This actionListener plays the game again
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameViewTest.main(null);
            }
        });
        //
        //This actionListener closes the program
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //these 2 lines add the buttons to the panel
        panel.add(playAgain);
        panel.add(exit);

        add(panel, BorderLayout.NORTH);//This adds the panel to the top of the frame



        //This section of code gets the file with the games scores on and creates a list for the scores
        BufferedReader br = new BufferedReader(new FileReader("All_high_scores.txt"));
        List<Integer> numbers = new ArrayList<Integer>();
        String line = null;

        //this loop goes through the file and adds all the numbers to the list for the scores
        while((line = br.readLine()) != null)
        {
            String []stringNumbers = line.split(" ");
            for(String stringNumber : stringNumbers)
            {
                numbers.add(Integer.parseInt(stringNumber));
            }
        }
        br.close();//closes the file


        //This would be where the weekly high-scores code would be



        JPanel panel2 = new JPanel();//creates a new panel for the players score
        JLabel scoreLabel = new JLabel();//creates the label for the score
        scoreLabel.setText("Your score was: " + numbers.get(numbers.size() - 1));//sets the labels text
        panel2.add(scoreLabel);//adds the label to the panel
        add(panel2, BorderLayout.CENTER);//adds the panel to the frame



        JTabbedPane tab = new JTabbedPane();//creates a new tab pane
        add(tab, BorderLayout.SOUTH);//This adds the tab pane below the first panel on the frame
        //
        //this section of code creates a new textArea for the first tab and formats it
        JTextArea scoresArea1 = new JTextArea();
        scoresArea1.setFont(scoresArea1.getFont().deriveFont(17.5f));
        scoresArea1.setLineWrap(true);
        scoresArea1.setWrapStyleWord(true);
        scoresArea1.setEditable(false);
        //
        //this section of code creates a new textArea for the second tab and formats it
        JTextArea scoresArea2 = new JTextArea();
        scoresArea2.setFont(scoresArea2.getFont().deriveFont(17.5f));
        scoresArea2.setLineWrap(true);
        scoresArea2.setWrapStyleWord(true);
        scoresArea2.setEditable(false);
        //
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



        //this section adds needed text to the TextArea of tab 2
        scoresArea2.append("Weekly top 10 High-scores:\n\n");
        scoresArea2.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        scoresArea2.append("Rankings     ||     High-scores\n");
        scoresArea2.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int rankingNumber2 = 1;



        scoresArea2.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");



        //these lines add the tabs to the tab pane
        tab.addTab("All high-scores", null, scoresArea1);
        tab.addTab("Weekly high-scores", null, scoresArea2);

        //This section sets all the settings for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        this.setVisible(true);
        setLocationRelativeTo(null);
        setBackground(Color.black);
        repaint();
    }
}
