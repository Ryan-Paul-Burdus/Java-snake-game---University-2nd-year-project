package Game;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Ryan on 29/11/2017.
 */

public class GameFrame extends JFrame
{
    public Component comp;//creates a component called comp
    public GameFrame(Component comp, String title)
    {
        super(title);//uses the title from the GameViewTest class
        this.comp = comp;//sets the component
        getContentPane().add(BorderLayout.SOUTH, comp);//gets the content from GameViewTest

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops the program when the games frame is closed
        setResizable(false);//makes it so you cant re-size the frame
        pack();//sets the frame to have tightly fit all items within it
        setBackground(Color.gray);//sets the background of the frame
        this.setVisible(true);//has the frame be visible
        setLocationRelativeTo(null);//has the frame set to the middle of the screen

        repaint();//paints the frame
    }
}
