package dev.jasonr.snakegame;

public class Launcher {

	//Main Method
	public static void main(String[] args) {
		Game game = new Game("Snake", 800, 800);
		game.start();
	}
}
