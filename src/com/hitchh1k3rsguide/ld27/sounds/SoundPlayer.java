package com.hitchh1k3rsguide.ld27.sounds;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class SoundPlayer
{

	private static HashMap<String, SFX> sounds = new HashMap<String, SFX>();
	private static HashMap<String, Audio> music = new HashMap<String, Audio>();
	private static Audio lastMusic = null;
	private static String lastSong = "";

	private static class SFX
	{
		private Audio[] effects;
		private int rand;
		private float gain;
		public SFX(String name, int num, float volume)
		{
			gain = volume;
			rand = num;
			if(num == 0)
			{
				effects = new Audio[1];
				try
				{
					effects[0] = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/Audio/"+name+".wav"));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				effects = new Audio[num];
				for(int i = 0; i < num; ++i)
				{
					try
					{
						effects[i] = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/Audio/"+name+(i+1)+".wav"));
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		public void play()
		{
			if(rand > 0)
				effects[(int) (Math.random()*rand)].playAsSoundEffect(1, gain, false);
		}
	};

	public static void init()
	{
//		sounds.put("explode", new SFX("explosion", 5, 0.5F));
//		sounds.put("alarm", new SFX("alarm", 0, 1));
//		sounds.put("pickup", new SFX("pickup", 0, 1));
//		sounds.put("terminal", new SFX("terminal", 0, 1));
		try
		{
			music.put("run", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-run damn you.ogg")));
			music.put("mdsitw", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-most dancable song in the world.ogg")));
			music.put("Rrun", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-run damn you-REV.ogg")));
			music.put("Rmdsitw", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-most dancable song in the world-REV.ogg")));
			music.put("game", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-time out-LOW.ogg")));
			music.put("theme", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-theme-LOW.ogg")));
			music.put("tension", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-rising tension.ogg")));
			music.put("Rtension", AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("assets/Audio/LD-27-rising tension-REV.ogg")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void playSound(String key)
	{
		sounds.get(key).play();
	}

	public static void playMusic(String key)
	{
		lastSong = key;
		lastMusic = music.get(key);
		lastMusic.playAsMusic(1, 1, true);
	}

	public static void stopMusic()
	{
		if(lastMusic != null)
			lastMusic.stop();
	}

	public static void reverseSong(boolean fullSpeed)
	{
		if(lastSong != "")
		{
			String key;
			if(lastSong.charAt(0) == 'R')
				key = lastSong.substring(1);
			else
				key = "R"+lastSong;
//			float duration;
//			if(key.endsWith("run"))
//				duration = 33.436745F;
//			else
//				duration = 25.263F;
//			float secTime = (duration-lastMusic.getPosition());

			lastSong = key;			
			lastMusic = music.get(key);
			lastMusic.playAsMusic(fullSpeed?1:0, 1, true);
//			lastMusic.setPosition(secTime);
		}
	}

	public static void update()
	{
		SoundStore.get().poll(0);
	}

}
