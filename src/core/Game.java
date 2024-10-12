package core;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import java.math.*;
import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

public class Game extends BasicGameState 
{	
	private int id;
	public Target ball;
	public ArrayList<Target> fruits;
	public ArrayList<TargetFade> fadingFruits;
	public static TrueTypeFont timerFont;
	public static TrueTypeFont dataFont;
	Image bg;
	Image bg_resized;
	
	int lemons;
	int apples;
	int coconuts;
	int frames;
	int second;
	int basket;
	int applesTotal;
	int points;
	final int TIME_LIMIT =20;
	
	
	public Game(int id) 
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
		gc.setShowFPS(true);
		lemons =0;
		apples = 0;
		coconuts =0;
		basket =0;
		frames = 0;
		applesTotal =0;
		
		fruits = new ArrayList<Target>();
		fadingFruits = new ArrayList<TargetFade>();
		loadFonts();
		bg = new Image("res/woodBackground.png");
		bg_resized = bg.getScaledCopy(Main.getScreenHeight(),Main.getScreenHeight());
		
		
		//images load images and load fonts.	
		
	}
	
	public static void loadFonts()
	{
		timerFont = new TrueTypeFont(new Font ("Cooper Black", Font.BOLD, 50), false);
		dataFont = new TrueTypeFont (new Font ("Times New Roman", Font.PLAIN, 15), false);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
		
		//game stops when timer ends
		frames ++;
		second = TIME_LIMIT - frames/ Main.FRAMES_PER_SECOND;
		if (second<0)
		{
			Main.total = basket;
			Main.points = points;
			sbg.enterState(Main.END_ID);
		}
		
		
		if ((applesTotal==0)&&(Math.random()<0.005))
		{
			// randomly creates targets so always total of 3
			int temp_x = (int) (Math.random()*Main.getScreenWidth()/2+Main.getScreenWidth()/4);

			for (int i=0;i<4;i++)
			{
				TargetTwo t2 = new TargetTwo(temp_x);
				fruits.add(t2);
				applesTotal++;
			}
		}
		
		if (fruits.size() < 2)
		{
			if (Math.random()< .5)
			{
				TargetOne t1 = new TargetOne();
				fruits.add(t1); 
			}
			else
			{
				TargetThree t3 = new TargetThree();
				fruits.add(t3); 
			}
		}
		
		//updates each fruit and removes any that are too low
		//Creates a fading fruit where each fruit once was
		for (int i=0;i<fruits.size();i++)
		{
			fruits.get(i).update();

			
			if (!(fruits.get(i).inBounds()))
			{
				if (fruits.get(i).getType().equals("apple"))
				{
					applesTotal --;
				}
				fruits.remove(i);
			}
		}
		
		for (int f=0;f<fadingFruits.size();f++)
		{
			fadingFruits.get(f).update();
			if (!fadingFruits.get(f).alive())
			{
				fadingFruits.remove(f);
			}
		}
		
		points = 3*apples + 2*coconuts + lemons;
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		
		//renders background
		for (int i=0;i<3;i++)
		{
			g.drawImage(bg_resized, i*Main.getScreenHeight(), 0);
		}
		
		
		//render all fruits
		for (TargetFade ff: fadingFruits)
		{
			ff.render();
		}
		
		for (Target f: fruits)
		{
			f.render(g);
		}
		
		
		//render values.
		g.setFont(timerFont);
		
		if (second <= 5)
		{
			g.setColor(Color.red);
			g.drawString(""+second, 30, 25);
			g.setColor(Color.white);
		}
		else
		{
			g.setColor(Color.white);
			g.drawString(""+second, 30, 25);
		}
		
		g.drawString("Fruits: "+basket, 30, 85);
		g.setColor(Color.red);
		g.drawString("Points: "+ points, 30, 145);
		
		

		
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
		
		
		for (int i=0;i<fruits.size();i++)
		{
			if (fruits.get(i).isMouseOver(x, y))
			{
				basket ++;
				if(fruits.get(i).getType().equals("apple"))
				{
					apples ++;
					applesTotal --;
				}
				else if(fruits.get(i).getType().equals("coconut"))
				{
					coconuts ++;
				}
				else if(fruits.get(i).getType().equals("lemon"))
				{
					lemons ++;
				}
				
				
				//creates a fading fruit in place of caught fruit.
				Target f = fruits.get(i);
				try {
					TargetFade ff = new TargetFade(f.getType(), second, f.getX(), f.getY());
					fadingFruits.add(ff);
					fruits.remove(i);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
		}
		// This code happens every time the user presses the mouse
	}

	
	


}
