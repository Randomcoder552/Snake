package dev.jasonr.snakegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 62, height = 62;
	
	public static BufferedImage start_button, title;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet1.png"));
		
		start_button = sheet.crop(0, 0, width, height);
		title = sheet.crop(width, 0, width, height);
	}
	
}
