package core;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;


public class TargetFade {
	
	private int initSec;
	private int x;
	private int y;
	private int r;
	private int c;
	private int frame =0;
	final int LIFE_SPAN = 5;
	
	String type;
	Image frameset;
	Image framesetScaled;
	Image currentFrame;
	SpriteSheet framesetSheet;
	
	
	public TargetFade(String type, int initSec, int x, int y) throws SlickException
	{
		this.initSec = initSec;
		this.type = type;
		this.x = x;
		this.y = y;
		r=1;
		c=0;

		
		frameset = new Image ("res/"+type+"Fade.png");
		framesetScaled = frameset.getScaledCopy(600, 600);
		framesetSheet = new SpriteSheet(framesetScaled, 300,300);
	}

	final int INIT_SEC = initSec;
	final int X = x;
	final int Y = y;
	
	public void update()
	{
		frame ++;
		if (frame % 120==0)
		{
			r=0;
			c=1;
		}
		
		currentFrame = framesetSheet.getSprite(r,c);
	}
	
	public void render ()
	{
		
		currentFrame.draw(x,y);
	}
	
	public boolean alive()
	{
		if (frame > 240)
		{
			return false;
		}
		return true;
	}
}
