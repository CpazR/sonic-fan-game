package badniks;

import datatypes.Vector;
import rendering.Camera;
import rendering.Shader;

public abstract class Badnik {
	public int type;
	public int destroy;
	public Vector pos;
	
	public abstract void update(float dt);	
	public abstract void draw(int scaleX, int scaleY, float dt, Shader shader, Camera camera);
	public abstract void destroy();
}