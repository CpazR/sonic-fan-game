package scenes;

import rendering.Camera;

public abstract class Scene {
	protected Camera camera;

	public Scene() {}

	public abstract void init();
	public abstract void update(float dt);
	// called when exiting a scene, performs any operations needed like stopping music or saving stage specific data such as score
	public abstract void exit();

	public Camera camera() {return(camera);}
}
