package game;

import java.io.*;
import java.util.Date;
import java.sql.Timestamp;

public class Logger
{
    //private static Date date = new Date();
    private static final File log = new File("log.txt");
    
    public static void log(String message)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            if(!log.exists())log.createNewFile();
            timestamp();
            out.write(timestamp() + message);
            out.newLine();
            System.out.println(timestamp() + message);
            out.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
    
	public static void log(Object o, String message) 
	{
        log(o.getClass().getName() + " reports " + message);
	}
	
    public static void err(Throwable error)
    {
        try
        {
            if(!log.exists())log.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            out.write(timestamp() + "WARNING: " + error);
            out.newLine();
            out.write(error.toString());
            out.newLine();
            out.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
    
    public static void err(Object o, Throwable error)
    {
        try
        {
            if(!log.exists())log.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            out.write(timestamp() + "WARNING:  " + o + "reports " + error);
            out.newLine();
            out.write(error.toString());
            out.newLine();
            out.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
    
    public static void err(Object o, String message)
    {
        try
        {
            if(!log.exists())log.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            out.write(timestamp() + "WARNING:  " + o + " reports " + message);
            out.newLine();
            out.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
    
    public static void err(String message)
    {
        try
        {
            if(!log.exists())log.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            out.write(timestamp() + "WARNING:  " + message);
            out.newLine();
            out.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
    
    public static void clearLog()
    {
        try
        {
            if(!log.exists())log.createNewFile();
            else
            {
                BufferedWriter out = new BufferedWriter(new FileWriter(log));
                out.write("");
                System.out.println("Log cleared.");
                out.close();
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    private static String timestamp()
    {
        return ("[" + new Timestamp(new Date().getTime()) + "]  ");
    }

	public static void log(char character) 
	{
		log(Character.toString(character));
	}

	public static void log(int num) 
	{
		log(Integer.toString(num));
	}
}