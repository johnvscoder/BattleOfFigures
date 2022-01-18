package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {
	public static Font fontSmall, fontBig;
	
	
	//Sonidos corregidos
	public static Clip backgroundMusic;
	public static Clip laserPlayer, lostPlayer;
	public static Clip laserEnemy1, lostEnemy1,
						laserEnemy2, lostEnemy2,
						laserEnemy3, lostEnemy3,
						laserEnemy4, lostEnemy4,
						laserEnemy5, lostEnemy5,
						laserEnemy6, lostEnemy6,
						laserEnemy7, lostEnemy7,
						laserEnemy8, lostEnemy8;
	/////////////////////////////////
	
	
	public static int maxCount = 21;
	public static int count = 0;
	public static boolean end = false;
	
	public static void init() {
		
		fontSmall = loadFont("/fonts/KenneyFutureNarrow.ttf", 12);
		fontBig = loadFont("/fonts/KenneyFutureNarrow.ttf", 48);
		
		//Correccion de sonidos
		backgroundMusic = loadSound("/sounds/backgroundmusic.wav");
		
		laserPlayer = loadSound("/sounds/laserP.wav");
		lostPlayer = loadSound("/sounds/lostP.wav");
		
		laserEnemy1 = loadSound("/sounds/laserE1.wav");
		laserEnemy2 = loadSound("/sounds/laserE2.wav");
		laserEnemy3 = loadSound("/sounds/laserE3.wav");
		laserEnemy4 = loadSound("/sounds/laserE4.wav");
		laserEnemy5 = loadSound("/sounds/laserE5.wav");
		laserEnemy6 = loadSound("/sounds/laserE6.wav");
		laserEnemy7 = loadSound("/sounds/laserE7.wav");
		laserEnemy8 = loadSound("/sounds/laserE8.wav");
		
		lostEnemy1 = loadSound("/sounds/lostE1.wav");
		lostEnemy2 = loadSound("/sounds/lostE2.wav");
		lostEnemy3 = loadSound("/sounds/lostE3.wav");
		lostEnemy4 = loadSound("/sounds/lostE4.wav");
		lostEnemy5 = loadSound("/sounds/lostE5.wav");
		lostEnemy6 = loadSound("/sounds/lostE6.wav");
		lostEnemy7 = loadSound("/sounds/lostE7.wav");
		lostEnemy8 = loadSound("/sounds/lostE8.wav");
		////////////////////////
		
		
		end = true;
	}
	
	public static Font loadFont(String path, int size) {
		count++;
		return Loader.loadFont(path, size);
	}
	
	public static Clip loadSound(String path) {
		count++;
		return Loader.loadSound(path);
	}
}
