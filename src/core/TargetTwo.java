package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TargetTwo extends Target{
	//apple
	
	final int SIZE =200;
	
	public TargetTwo(int x_range) throws SlickException
	{
		super();
		target = new Image("res/apple.png");
		targetScale = target.getScaledCopy(SIZE,SIZE);
		y=(int)(Math.random()*-500);
		x= x_range + (int)(Math.random()*500-100); 
		y_velocity =0;
		type = "apple";
		g=0.1;
	}
	

}
