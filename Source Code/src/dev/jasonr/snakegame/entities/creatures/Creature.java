package dev.jasonr.snakegame.entities.creatures;

import dev.jasonr.snakegame.entities.Entity;

public abstract class Creature extends Entity{

	protected int health;
	
	public Creature(int x, int y) {
		super(x, y);
		health = 10;
	}
	
}
