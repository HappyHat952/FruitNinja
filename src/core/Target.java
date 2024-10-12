package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class Target {
	
	Image target;
	Image targetScale;
	final int SIZE = 300;
	double g = 0.3;
	final double INIT_Y_VEL = -30;
	int x;
	int y;
	double y_velocity;
	String type;

	
	
	public Target() throws SlickException
	{
		y_velocity = INIT_Y_VEL;
		target = new Image("res/bigBall.png");
		targetScale = target.getScaledCopy(SIZE,SIZE);
		y = Main.getScreenHeight()-400;
		x = (int)( Math.random()*Main.getScreenWidth());
		type = "default";
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(targetScale, x, y);
	}
	
	public void update()
	{
		//vertical velocity is decreasing as time increases.
		
		y = (int)(y+ y_velocity);
		y_velocity = y_velocity + g;
		

		
		
	}
	
	public boolean getDead()
	{
		//Dies when reaches bottom of screen
		if (y>Main.getScreenHeight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public String getType()
	{
		return type;
	}
	
	public boolean isMouseOver(int mouseX, int mouseY)
	{
		if(dist(mouseX, mouseY, x+SIZE/2, y+SIZE/2)< SIZE/2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean inBounds()
	{
		if (y>Main.getScreenHeight())
		{
			return false;
		}
		else if ((x>Main.getScreenWidth()+100)||(x<-500))
		{
			return false;
		}
		return true;
		
	}
	
	private double dist(int x1, int y1, int x2, int y2)
	{
		double distance = Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
		return distance;
	}
	
}
