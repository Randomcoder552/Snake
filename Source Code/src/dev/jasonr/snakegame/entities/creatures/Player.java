package dev.jasonr.snakegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.jasonr.snakegame.Game;

public class Player extends Creature{
	

	//Snake Components
	Rectangle[] snake;
	int k;
	//Collision Boxes
	public static Rectangle boundsHead;
	public static Rectangle boundsLeft;
	public static Rectangle boundsRight;
	public static Rectangle boundsUp;
	public static Rectangle boundsDown;
	//Boolean to communicate if Apple should move
	public static boolean appleMove = false;
	//Movement Booleans
	private static boolean upDirection;
	private static boolean downDirection;
	private static boolean leftDirection;
	private static boolean rightDirection;
	//Other
	private Game game;
	
	//Constructor
	public Player(Game game, int x, int y) {
		super(x, y);
		this.game = game;
		init();
	}
	
	//Init Method
	private void init() {
		k = 1;
		snake = new Rectangle[k];
		snake[0] = new Rectangle(x, y, 20, 20);
		boundsHead = new Rectangle(x, y, 20, 20);
		upDirection = false;
		downDirection = false;
		leftDirection = false;
		rightDirection = false;
		boundsLeft = new Rectangle(0, 0, 5, 800);
		boundsRight = new Rectangle(795, 0, 5, 800);
		boundsUp = new Rectangle(0, 0, 800, 5);
		boundsDown = new Rectangle(0, 795, 800, 5);
	}
	
	public void expand() {
		
		k++;

		Rectangle[] newSnake = new Rectangle[k];
		
		for(int n = 0; n <= k - 2; n++) {
		  newSnake[n] = snake[n];
		}
		
		newSnake[k-1] = new Rectangle(12, 34, 20, 20);
		
		snake = newSnake;
		
	}

	//Tick Method (Detects Keyboard Input to move Player + Moves Snake's body & Collision Boxes with Snake's Head)
	@Override
	public void tick() {
		if(game.getKeyManager().up || game.getKeyManager().up2) {
			upDirection = true;
            rightDirection = false;
            leftDirection = false;
		}
		if(game.getKeyManager().down || game.getKeyManager().down2) {
			downDirection = true;
            rightDirection = false;
            leftDirection = false;
		}
		if(game.getKeyManager().left || game.getKeyManager().left2) {
			leftDirection = true;
            upDirection = false;
            downDirection = false;
		}
		if(game.getKeyManager().right || game.getKeyManager().right2) {
			rightDirection = true;
            upDirection = false;
            downDirection = false;
		}
		
		moveBody(snake);
		
		
		// this sets snake[0]
		if (upDirection == true) 
			boundsHead.y -= 3;
			
		if (downDirection == true)
			boundsHead.y += 3;			 
		
		if (leftDirection == true) 
			boundsHead.x -= 3;			 
		
		if (rightDirection == true) 			
			boundsHead.x += 3;

		snake[0].x = boundsHead.x;
		snake[0].y = boundsHead.y;
	}

	//Render Method
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		
		intersects(g);
		colissionWithWall();
		collisionWithBody();
		
		for(int i = 0; i < k; i++) {
		    g.fillRect(snake[i].x, snake[i].y, 20, 20);
		}
	}
		
	
	private void intersects(Graphics g) {
		//Detecting Collisions
		if(snake[0].intersects(Game.apple)) {
			if(appleMove == true) {
				return;
			} else {
				appleMove = true;
				for(int y = 0; y <= 7; y++) {
     				expand();
				}
				return;
			}
			
		}else {
			if(appleMove == false) {
				return;
			}else {
				appleMove = false;
			}
		}
		return;
	}
	
	public void colissionWithWall() {
		if(snake[0].intersects(boundsUp) || snake[0].intersects(boundsDown) || snake[0].intersects(boundsLeft) || snake[0].intersects(boundsRight)) {
			System.exit(1);
		}
	}
	
	public void collisionWithBody() {
		for(int i = 20; i < k; i++) {
			if(snake[0].intersects(snake[i])) {
				System.exit(1);
			}
		}
	}
	
	
	//Add to Snake's Body Method
		public void moveBody(Rectangle[] A) {
			if(k == 1) {
				return;
			}else {
			for(int i = k - 1; i != 0; i--) {			
				A[i].x = A[i - 1].x;				
				A[i].y = A[i - 1].y;
			}
			}
		}
}
