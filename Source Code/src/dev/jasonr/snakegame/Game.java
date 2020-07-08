package dev.jasonr.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.Random;

import dev.jasonr.snakegame.display.Display;
import dev.jasonr.snakegame.entities.creatures.Player;
import dev.jasonr.snakegame.gfx.Assets;
import dev.jasonr.snakegame.input.KeyManager;
import dev.jasonr.snakegame.input.MouseManager;
import dev.jasonr.snakegame.states.GameState;
import dev.jasonr.snakegame.states.MenuState;
import dev.jasonr.snakegame.states.State;

public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	public String title;
	public static int initX, initY;
	public static Rectangle apple;
	
	private boolean running = false;
	private Thread thread;
	
	private static MouseManager mouseManager;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public static int randomX, randomY;
	
	//States
	public static State gameState;
	public State menuState;
	
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) {
		//Display
		this.width = width;
		this.height = height;
		this.title = title;
		//Initial X + Y Of Apple || Starting Point of Apple
		initX = getRandomNumberInRange(50, 720);
		initY = getRandomNumberInRange(50, 720);
		//Input
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		apple = new Rectangle(initX, initY, 20, 20);
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(menuState);
	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here
		
		//Background
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		//Apple
		g.setColor(Color.red);
		g.fillRect(apple.x, apple.y, apple.width, apple.height);
		if(Player.appleMove == true) {
			appleMove();
		}
		
		//State
		if(State.getState() != null) {
			State.getState().render(g);
		}
				
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	//Get Random Number Method
		public static int getRandomNumberInRange(int min, int max) {
			if(min >= max) {
				throw new IllegalArgumentException("Max must be greater than Min!!!");
			}
			Random r = new Random();
			return r.nextInt((max - min) + 1) + min;
		}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		@SuppressWarnings("unused")
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
			    tick();
			    render();
			    ticks++;
			    delta--;
			}
			
			if(timer >= 1000000000) {
				//System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public static MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void appleMove() {
			randomX = getRandomNumberInRange(50, 720);
			randomY = getRandomNumberInRange(50, 720);
			apple.x = randomX;
			apple.y = randomY;
			return;
	}
	
	//Getters + Setters
	public Rectangle getApple() {
		return apple;
	}

	public void setApple(Rectangle apple) {
		Game.apple = apple;
	}
	
	
	
}
