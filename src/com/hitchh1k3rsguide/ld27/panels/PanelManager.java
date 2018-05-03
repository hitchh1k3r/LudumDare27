package com.hitchh1k3rsguide.ld27.panels;

import java.util.ArrayList;
import java.util.Hashtable;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.GameSettings;

public class PanelManager
{
	private Hashtable<Enums.displayLayers, ArrayList<IPanel>> panelLayers = new Hashtable<Enums.displayLayers, ArrayList<IPanel>>();
	private Enums.displayLayers[] layers = new Enums.displayLayers[]{Enums.displayLayers.SYSTEM, Enums.displayLayers.MENU, Enums.displayLayers.INTERFACE, Enums.displayLayers.HUD, Enums.displayLayers.GAME, Enums.displayLayers.BACKGROUND};
	private static PanelManager instance = new PanelManager();
	private static double timeScale;
	private Enums.displayLayers lastLayer;
	private int lastID;

	public PanelManager()
	{
		timeScale = 1;
		for(Enums.displayLayers layer : layers)
			panelLayers.put(layer, new ArrayList<IPanel>());
	}

	public static PanelManager getInstance()
	{
		return instance;
	}

	public void handleInputEvents()
	{
		while(Keyboard.next())
		{
			boolean stop = false;
			for(Enums.displayLayers layer : layers)
			{
				for(int i = panelLayers.get(layer).size()-1; !stop && i>=0; --i)
				{
					stop = panelLayers.get(layer).get(i).onKey(Keyboard.getEventKey(), Keyboard.getEventKeyState());
				}
				if(stop)
					break;
			}
		}
		double mouseXScale = (double)GameSettings.GAME_WIDTH/(double)GameSettings.SCREEN_WIDTH;
		double mouseYScale = (double)GameSettings.GAME_HEIGHT/(double)GameSettings.SCREEN_HEIGHT;
		while(Mouse.next())
		{
			if(Mouse.getEventButton() == -1)
			{
				boolean stop = false;
				for(Enums.displayLayers layer : layers)
				{
					for(int i = panelLayers.get(layer).size()-1; !stop && i>=0; --i)
					{
						stop = panelLayers.get(layer).get(i).onMouseMove((int)(mouseXScale*(double)Mouse.getX()), GameSettings.GAME_HEIGHT-(int)((double)Mouse.getY()*mouseYScale));
					}
					if(stop)
						break;
				}
			}
			else
			{
				boolean stop = false;
				for(Enums.displayLayers layer : layers)
				{
					for(int i = panelLayers.get(layer).size()-1; !stop && i>=0; --i)
					{
						stop = panelLayers.get(layer).get(i).onMouseClick((int)(mouseXScale*(double)Mouse.getX()), GameSettings.GAME_HEIGHT-(int)((double)Mouse.getY()*mouseYScale), Mouse.getEventButton(), Mouse.getEventButtonState());
					}
					if(stop)
						break;
				}
			}
		}
	}

	public void setTimeScale(double scale)
	{
		timeScale = scale;
	}

	public double getTimeScale()
	{
		return timeScale;
	}

	public void update(double frameTime)
	{
		for(Enums.displayLayers layer : layers)
		{
			for(int i = panelLayers.get(layer).size()-1; i>=0; --i)
			{
				panelLayers.get(layer).get(i).update(frameTime*timeScale);
			}
		}
	}

	public void draw()
	{
		for(int l = layers.length-1; l>=0; --l)
		{
			for(int i = 0; i<panelLayers.get(layers[l]).size(); ++i)
			{
				panelLayers.get(layers[l]).get(i).draw();
			}
		}
	}

	public int addPanel(IPanel panel, Enums.displayLayers layer) // add panel to top
	{
		panelLayers.get(layer).add(panel);
		int index = panelLayers.get(layer).size()-1;
		panelLayers.get(layer).get(index).onAttach();
		lastLayer = layer;
		lastID = index;
		return index; 
	}

	public boolean removePanel(Class type) // remove top most panel of type
	{
		for(Enums.displayLayers layer : layers)
		{
			if(removePanel(type, layer))
				return true;
		}
		return false;
	}

	public boolean removePanel(Class type, Enums.displayLayers layer) // remove top most panel of type in layer
	{
		for(int i = 0; i<panelLayers.get(layer).size(); ++i)
		{
			if(type.isInstance(panelLayers.get(layer).get(i)))
			{
				panelLayers.get(layer).get(i).onDetach();
				panelLayers.get(layer).remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean removePanel(int index, Enums.displayLayers layer) // remove panel by explicit ID
	{
		panelLayers.get(layer).get(index).onDetach();
		return panelLayers.get(layer).remove(index) != null;
	}

	public IPanel getPanel(Class type) // remove top most panel of type
	{
		for(Enums.displayLayers layer : layers)
		{
			IPanel temp = getPanel(type, layer);
			if(temp != null)
			{
				return temp;
			}
		}
		return null;
	}

	public IPanel getPanel(Class type, Enums.displayLayers layer) // remove top most panel of type in layer
	{
		for(int i = panelLayers.get(layer).size()-1; i>=0; --i)
		{
			if(type.isInstance(panelLayers.get(layer).get(i)))
			{
				return panelLayers.get(layer).get(i);
			}
		}
		return null;
	}

	public IPanel getPanel(int index, Enums.displayLayers layer) // remove panel by explicit ID
	{
		return panelLayers.get(layer).get(index);
	}

	public Enums.displayLayers getLastLayer()
	{
		return lastLayer;
	}

	public int getLastID()
	{
		return lastID;
	}

}
