package dev.jasonr.snakegame.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.jasonr.snakegame.Game;

public class UIManager {
	
	private Game game;
	private ArrayList<UIObject> objects;
	
	public UIManager(Game game) {
		this.game = game;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for(UIObject o : objects)
			o.tick();
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}
	
	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseMove(e);
	}
	
	public void onMouseRelease(MouseEvent e){
		for(UIObject o : objects)
			o.onMouseRelease(e);
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	
	//Getters + Setters
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
