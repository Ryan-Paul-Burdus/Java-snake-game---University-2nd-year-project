package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by rb16861 on 04/12/2017.
 */

public class MainSnakeGame
{
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("CE203 Assignment2: Ryan Burdus, 1603670");//creates a new frame

        //creates 2 panels
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();

        //creates a play button
        JButton playButton = new JButton();
        playButton.setText("Play");
        //
        //creates an exit button
        JButton exitButton = new JButton();
        exitButton.setText("Exit");
        //
        // creates and gets a logo image
        ImageIcon logo = new ImageIcon("C:/Users/Ryan/Desktop/snake-01.png");
        JLabel label = new JLabel(logo);


        //adds the logo and all the buttons to the panels
        panel.add(label);
        panel2.setLayout(new GridBagLayout());
        panel2.add(playButton);
        panel2.add(exitButton);

        //adds the panels to the frame
        menuFrame.add(panel, BorderLayout.NORTH);
        menuFrame.add(panel2, BorderLayout.SOUTH);

        //These 4 actionListeners add functions to the buttons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuFrame.dispose();//closes the menu when the game is opened
                GameViewTest.main(null);//opens the game
            }
        });
        //
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);//closes and stops the program
            }
        });

        //this section sets all the settings for the frame
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.pack();
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }
}
