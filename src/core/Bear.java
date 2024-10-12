package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Bear extends Target{
	
	public Bear() throws SlickException
	{
		super();
		target = new Image("res/bear.png");
		targetScale = target.getScaledCopy(SIZE,SIZE);
		
	}
	
	

}
