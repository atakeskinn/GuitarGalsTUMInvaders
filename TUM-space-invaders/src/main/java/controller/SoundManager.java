package controller;

public interface SoundManager {

	public static final String BACKGROUND_MUSIC_FILE = "audio/.mp3";
	public static final String PLAYER_SHOOT_SOUND_FILE = "audio/.mp3";
	public static final String ENEMY_SHOOT_SOUND_FILE = "audio/.wav";
	public static final String PLAYER_DEATH_SOUND_FILE = "audio/.wav";

	public void playBackgroundMusic();
	public void playPlayerShootSound();
	public void playEnemyShootSound();
	public void playPlayerDeathSound();
	public void playEnemyDeathSound();
	public void playWaveEndSound();
    public void stopBackgroundMusic();
}
