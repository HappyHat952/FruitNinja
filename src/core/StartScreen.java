package core;

import org.newdawn.slick.Color;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

public class StartScreen extends BasicGameState {
	private int id;
	public static TrueTypeFont titleFont;
	
	StateBasedGame sbg;
	Image bg;
	Image bg_resized;
	
	Image logo;
	Image logo_resized;
	

	public StartScreen(int id) throws SlickException
	{
		this.id = id;
		
		
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		this.sbg = sbg;
		gc.setShowFPS(true);
		loadFonts();
		bg = new Image("res/woodBackground.png");
		bg_resized = bg.getScaledCopy(Main.getScreenHeight(),Main.getScreenHeight());
		logo = new Image("res/fruitNinjaLogo.png");
		logo_resized = logo.getScaledCopy(3);
		
	}

	private void loadFonts()
	{
		titleFont = new TrueTypeFont (new Font("Times New Roman", Font.BOLD, 35), false);
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		//renders background
		g.setBackground(Color.cyan);
		
		//renders background
		for (int i=0;i<3;i++)
		{
			g.drawImage(bg_resized, i*Main.getScreenHeight(), 0);
		}
		
		g.drawImage(logo_resized, Main.getScreenWidth()/2-logo_resized.getWidth()/2, 300);
		
		g.setFont(titleFont);
		int w = titleFont.getWidth("Click to Start");
		g.drawString("Click to Start", Main.getScreenWidth()/2-logo_resized.getWidth()/2, 800);
		
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
		sbg.enterState(Main.GAME_ID);
	}
}
