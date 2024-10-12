package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TargetThree extends Target {
	//coconut

	int x_const;
	
	
	
	public TargetThree() throws SlickException
	{
		super();
		target = new Image("res/coconut.png");
		targetScale = target.getScaledCopy(SIZE,SIZE);
		type = "coconut";
		y_velocity = -25;
		x= (int) (Math.random()*Main.getScreenWidth()/4+Main.getScreenWidth()/8);
		y = Main.getScreenHeight() +(int)(Math.random()*50);
		
		if (Math.random()<.5)
		{
			x_const= -3;
		}
		else
		{
			x_const = 3;
		}
	}
	
	public void update()
	{
		super.update();
		x+=x_const;
	}
	
	

}
