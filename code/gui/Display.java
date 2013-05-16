package game.gui;

import java.awt.*;
import javax.swing.*;

public class Display extends JFrame
{
	private static final long serialVersionUID = 1L;
	Image iconImage = Texture.FRAME_ICON;
    public MenuGUI menu;
    public GameGUI game;
    public Display()
    {
        game = new GameGUI();
        menu = new MenuGUI();
        setIconImage(iconImage);
        setTitle("Project ACOMB");
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
    }
    public void showMenu()
    {
        add(menu);
    }
    public void showGame()
    {
        add(game);
        game.requestFocus();
    }
}