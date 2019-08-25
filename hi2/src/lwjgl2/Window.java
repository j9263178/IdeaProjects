package lwjgl2;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {
	private int width,height;
	
	public Window() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void createWindow(String Title) {
		
		try {
			Display.setTitle(Title);
			Display.setVSyncEnabled(true);
			Display.create();

	    	GL11.glEnable(GL11.GL_TEXTURE_2D);
	    	GL11.glEnable(GL11.GL_BLEND);
	    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void setSize(int width,int height) {
		
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isClosed() {
		return Display.isCloseRequested();
	}
	
	public void update() {
		Display.update();
        Display.sync(120);
	}
		
}
