package datatypes;

import static java.lang.Math.*;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Animation {
	public double timer;
	public int frame;
	private int[] durations;
	private int repeatFrame;
	public boolean finished; // only for first cycle
	private BufferedImage[] frames;
	
	public Animation() {}
	
	public Animation(BufferedImage[] frames, int[] durations, int repeatFrame) {
		this.frames = frames;
		this.durations = durations;
		this.repeatFrame = repeatFrame;
		
		if(repeatFrame == -1) {this.repeatFrame = frames.length - 1;}
	}
	public Animation(String path, String name, int[] durations, int repeatFrame) {
		frames = loadAnimation(path, name);
		this.durations = durations;
		this.repeatFrame = repeatFrame;
		
		if(repeatFrame == -1) {this.repeatFrame = frames.length - 1;}
	}
	
	public void reset() {
		timer = 0;
		frame = 0;
		finished = false;
	}
	
	public void set(int frame, double timer) {
		this.timer = timer;
		this.frame = frame;
	}
	
	public void update(double speed) {
		timer += speed;
		if(timer >= durations[frame]) {
			timer = 0;
			frame++;
			
			if(frame >= frames.length) {
				frame = repeatFrame;
				
				finished = true;
			}
		}
		
		/*System.out.println("timer = " + timer);
		System.out.println("frame = " + frame);
		System.out.println("length = " + frames.length);*/
	}
	
	public int[] getCurrentSize() {return(new int[]{frames[frame].getWidth(), frames[frame].getHeight()});}
	
	public BufferedImage[] loadAnimation(String path, String name) {
		int length = 0;
		
		while(true) {
			try {ImageIO.read(getClass().getResourceAsStream("/" + path + "/" + name + length + ".png"));}
			catch(Exception e) {break;}
			
			length++;
		}
		
		BufferedImage[] images = new BufferedImage[length];
		
		try {for(int i = 0; i < length; i++) {images[i] = ImageIO.read(getClass().getResourceAsStream("/" + path + "/" + name + i + ".png"));}}
		catch(Exception e) {e.printStackTrace();}
		
		return(images);
	}
	
	public void draw(Graphics2D graphics, int x, int y) {graphics.drawImage (frames[frame], x, y, null);}
	public void draw(Graphics2D graphics, double x, double y) {graphics.drawImage(frames[frame], (int)x, (int)y, null);}
	
	public void draw(Graphics2D graphics, int x, int y, int xScale, int yScale) {graphics.drawImage(frames[frame], x, y, frames[frame].getWidth() * xScale, frames[frame].getHeight() * yScale, null);}
	public void draw(Graphics2D graphics, double x, double y, int xScale, int yScale) {graphics.drawImage(frames[frame], (int)x, (int)y, frames[frame].getWidth() * xScale, frames[frame].getHeight() * yScale, null);}
	
	public void draw(double x, double y, int xScale, int yScale, double originX, double originY, double angle, Graphics2D graphics) {draw(graphics, (int)x, (int)y, xScale, yScale, (int)originX, (int)originY, angle);}
	public void draw(Graphics2D graphics, int x, int y, int xScale, int yScale, int originX, int originY, double angle) {
		AffineTransform backup = graphics.getTransform();
	    AffineTransform a = AffineTransform.getRotateInstance(-angle - PI / 2, originX, originY);
	    
	    graphics.setTransform(a);
	    graphics.drawImage(frames[frame], x, y, frames[frame].getWidth() * xScale, frames[frame].getHeight() * yScale, null);
		graphics.setTransform(backup);
	}
}