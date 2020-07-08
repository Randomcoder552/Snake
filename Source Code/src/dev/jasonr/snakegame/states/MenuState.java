package dev.jasonr.snakegame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.jasonr.snakegame.Game;
import dev.jasonr.snakegame.gfx.Assets;
import dev.jasonr.snakegame.ui.ClickListener;
import dev.jasonr.snakegame.ui.UIImageButton;
import dev.jasonr.snakegame.ui.UIManager;

public class MenuState extends State{
	
	private UIManager uiManager;

	public MenuState(Game game) {
		super(game);
		uiManager = new UIManager(game);
		Game.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(280, 200, 200, 200, Assets.start_button, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(Game.gameState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		g.drawImage(Assets.title, 240, 50, 300, 300, null);
		g.setColor(Color.white);
		g.fillRect(100, 100, 20, 20);
	}

}
