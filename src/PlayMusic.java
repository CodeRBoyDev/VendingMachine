import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

import sun.audio.*;

import javax.swing.JOptionPane;


public class PlayMusic {
	public static FloatControl gainControl;
	

	public static void playMusic(String musicLocation){
		
		try
		{
			File musicPath = new File(musicLocation);
			if(musicPath.exists()){
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				//	gainControl.setValue(-10.0f); 
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				System.out.print("Welcome");
				//JOptionPane.showMessageDialog(null, "Press OK to stop playing");
			}
			else
			{
				System.out.println("Can't find file");
				
			}
		}
		
		catch(Exception ex){ex.printStackTrace();};
		
	
	}
	}
	
	
	
	
	

