package game.gui;

import javax.swing.*;
import java.awt.event.*;
import game.Game;
public class MenuGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	JButton start = new JButton("New Game");
    JButton load = new JButton("Load Game");
    
    public MenuGUI()
    {
        start.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) 
            { 
                Game.window.showGame();
            } 
        });
    }
}