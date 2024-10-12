package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TargetOne extends Target {
	//lemon
	
	
	public TargetOne() throws SlickException
	{
		super();
		target = new Image("res/lemon.png");
		targetScale = target.getScaledCopy(SIZE,SIZE);
		y=Main.getScreenHeight()-SIZE;
		x=-200;
		y_velocity=2;
		type = "lemon";
	}
	
	public void update()
	{
		x+=10;
		
		//bounces as it travels.
		y = (int)(y+ y_velocity);
		y_velocity = y_velocity + g;
		if (y>Main.getScreenHeight()-SIZE)
		{
			y_velocity *=-1;
		}
	}
	
	
	

}
