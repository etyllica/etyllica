package br.com.etyllica.nucleo.midia;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

/**
*   Class for playing MP3 files.
*/
public class MusicaMP3
{
    private String filename;
    private Player player;

    public MusicaMP3(String filename)
    {
        this.filename = filename;
    }

    public void close() { 
    	if (player != null) 
    		player.close(); 
    }

    // play the MP3 file to the sound card
    public void play()
    {
        try
        {
            ClassLoader cl = this.getClass().getClassLoader();
            
            System.out.println("caminho = "+cl.getResource(filename));
            
            InputStream myStream = cl.getResourceAsStream(filename);
            BufferedInputStream bis = new BufferedInputStream(myStream);
            player = new Player(bis);
        }
        catch (Exception e)
           {    }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }

}