package org.newdawn.spaceinvaders.lwjgl;

import java.awt.event.KeyEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.spaceinvaders.GameWindow;
import org.newdawn.spaceinvaders.GameWindowCallback;

/**
 * An implementation of GameWindow that will use OPENGL (JOGL) to 
 * render the scene. Its also responsible for monitoring the keyboard
 * using AWT.
 * 
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class LWJGLGameWindow implements GameWindow {
  
	/** The callback which should be notified of window events */
	private GameWindowCallback callback;
  
	/** True if the game is currently "running", i.e. the game loop is looping */
	private boolean gameRunning = true;
  
	/** The width of the game display area */
	private int width;
  
	/** The height of the game display area */
	private int height;

	/** The loader responsible for converting images into OpenGL textures */
	private TextureLoader textureLoader;
  
	/** Title of window, we get it before our window is ready, so store it till needed */
	private String title;
	
	/**
	 * Create a new game window that will use OpenGL to 
	 * render our game.
	 */
	public LWJGLGameWindow() {
	}
	
	/**
	 * Retrieve access to the texture loader that converts images
	 * into OpenGL textures. Note, this has been made package level
	 * since only other parts of the JOGL implementations need to access
	 * it.
	 * 
	 * @return The texture loader that can be used to load images into
	 * OpenGL textures.
	 */
	TextureLoader getTextureLoader() {
		return textureLoader;
	}
	
	/**
	 * Set the title of this window.
	 *
	 * @param title The title to set on this window
	 */
	public void setTitle(String title) {
	    this.title = title;
	    if(Display.isCreated()) {
	    	Display.setTitle(title);
	    }
	}

	/**
	 * Set the resolution of the game display area.
	 *
	 * @param x The width of the game display area
	 * @param y The height of the game display area
	 */
	public void setResolution(int x, int y) {
		width = x;
		height = y;
	}
	
	/**
	 * Sets the display mode for fullscreen mode
	 */
	private boolean setDisplayMode() {
		try {
			// get modes
//			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(
//					width, height, -1, -1, -1, -1, 60, 60);
		    DisplayMode[] dm = Display.getAvailableDisplayModes();

		    Display.setDisplayMode(dm[0]);
//			org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
//					"width=" + width,
//					"height=" + height,
//					"freq=" + 60,
//					"bpp=" 	+ org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel() });

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Unable to enter fullscreen, continuing in windowed mode");
		}

		return false;
	}
	
	/**
	 * Start the rendering process. This method will cause the display to redraw
	 * as fast as possible.
	 */
	public void startRendering() {
		try {                
			setDisplayMode();
			Display.create();
			
			// grab the mouse, dont want that hideous cursor when we're playing!
			Mouse.setGrabbed(true);
  
			// enable textures since we're going to use these for our sprites
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			// disable the OpenGL depth test since we're rendering 2D graphics
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			
			GL11.glOrtho(0, width, height, 0, -1, 1);
			
			textureLoader = new TextureLoader();
			
			if(callback != null) {
				callback.initialise();
			}
		} catch (LWJGLException le) {
			callback.windowClosed();
		}
    
		gameLoop();
	}

	/**
	 * Register a callback that will be notified of game window
	 * events.
	 *
	 * @param callback The callback that should be notified of game
	 * window events. 
	 */
	public void setGameWindowCallback(GameWindowCallback callback) {
		this.callback = callback;
	}
	
	/**
	 * Check if a particular key is current pressed.
	 *
	 * @param keyCode The code associated with the key to check 
	 * @return True if the specified key is pressed
	 */
	public boolean isKeyPressed(int keyCode) {
		// apparently, someone at decided not to use standard 
		// keycode, so we have to map them over:
		switch(keyCode) {
		case KeyEvent.VK_SPACE:
			keyCode = Keyboard.KEY_SPACE;
			break;
		case KeyEvent.VK_LEFT:
			keyCode = Keyboard.KEY_LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			keyCode = Keyboard.KEY_RIGHT;
			break;
		}    
		
		return org.lwjgl.input.Keyboard.isKeyDown(keyCode);
	}
  
	/**
	 * Run the main game loop. This method keeps rendering the scene
	 * and requesting that the callback update its screen.
	 */
	private void gameLoop() {
		while (gameRunning) {
			// clear screen
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			
			// let subsystem paint
			if (callback != null) {
				callback.frameRendering();
			}
			
			// update window contents
			Display.update();
			
			if(Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				gameRunning = false;
				Display.destroy();
				callback.windowClosed();
			}
		}
	}  
}