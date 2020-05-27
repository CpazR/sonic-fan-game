package objects;

import java.awt.Graphics2D;

import datatypes.Animation;
import datatypes.Vector;
import main.Loader;
import rendering.Camera;
import rendering.Renderer;
import rendering.Shader;

import static java.lang.Math.*;

public class Ring {
	public Vector pos;
	private Animation anim;
	public int destroy;
	
	public Ring(double x, double y) {
		pos = new Vector(x, y);
		anim = new Animation(Loader.ringAnim, new int[]{4, 4, 4, 4, 4, 4, 4, 4}, 0);
		destroy = 0;
	}
	
	public void manageAnimation(float dt) {
		for(int f = 1; f < min(60.0f / (1.0f / dt), 5); f++) {anim.update(1);}
		
		if(destroy == 2 && anim.finished) {destroy = 3;}
	}
	
	public void draw(int scaleX, int scaleY, Renderer r) {
		if(destroy == 1) {
			destroy = 2;
			anim = new Animation(Loader.sparkleAnim, new int[]{4, 4, 4, 5}, 0);
		}
		
		anim.draw(pos.x, pos.y, scaleX, scaleY, r);
	}
}
