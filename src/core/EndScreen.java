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

public class EndScreen extends BasicGameState {
	private int id;
	public static TrueTypeFont scoreFont;
	public static TrueTypeFont writingFont;
	private StateBasedGame sbg;
	
	int frame;
	int second;
	boolean canLeave = false;
	
	String timerMessage;
	String winLossMessage;
	Image bg;
	Image bg_resized;
	

	public EndScreen(int id) throws SlickException
	{
		this.id = id;
		winLossMessage = "kool";
		
		
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
		
	}
		
		

	private void loadFonts()
	{
		writingFont = new TrueTypeFont (new Font("Times New Roman", Font.BOLD, 35), false);
		scoreFont = new TrueTypeFont (new Font ("Cooper Black", Font.BOLD, 50), false);
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
		frame ++;
		second = (int)(frame/60);
		
		
		System.out.println(Main.points);
		if (((Main.points)>=30)&&((Main.points)<60))
		{
			winLossMessage = "GOOD ENOUGH";
		}
		else if((Main.points)<30)
		{
			winLossMessage = "WOMP WOMP";
		}
		else
		{
			winLossMessage = "INCREDIBLE!";
		}
		
		
		if (second==5)
		{
			canLeave = true;
		}
		
		if (!canLeave)
		{
			timerMessage = "You may click to leave in " +( 5-second) +" second(s)";
		}
		else
		{
			timerMessage = "You may click to leave";
		}
		
		
		
		
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
		
		
		
		g.setColor(Color.white);
		int wTime = writingFont.getWidth(timerMessage);
		int wMess = scoreFont.getWidth(winLossMessage);
		int wScore = scoreFont.getWidth("Fruits: "+Main.total);
		int wPoint = scoreFont.getWidth("Points: "+Main.points);
		g.setFont(writingFont);
		g.drawString(timerMessage, Main.getScreenWidth()/2-wTime/2, 300);
		
		g.setFont(scoreFont);
		g.setColor(Color.red);
		g.drawString(winLossMessage, Main.getScreenWidth()/2-wMess/2, 400);
		g.setColor(Color.white);
		g.drawString("Fruits: "+Main.total, Main.getScreenWidth()/2-wScore/2, 500);
		g.drawString("Points: "+Main.points, Main.getScreenWidth()/2-wPoint/2,600);		
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
		if (canLeave)
		{
			sbg.enterState(Main.START_ID);
		}
		
	}
}
