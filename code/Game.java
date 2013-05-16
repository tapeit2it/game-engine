package game;

import game.entity.Player;
import game.gui.Display;

public class Game
{
    static Player player;
    public static Display window = new Display();
    int width,height;
    public static void main(String[] args)
    {
    	Logger.clearLog();
        window.showGame();
    }
}