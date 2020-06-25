package controller;

public interface SoundManagerInterface {

	public static final String BACKGROUND_MUSIC_FILE = "RealMusic.wav";
	public static final String PLAYER_SHOOT_SOUND_FILE = "badabing.wav";
	public static final String ENEMY_SHOOT_SOUND_FILE = "ShortPew.wav";
	public static final String PLAYER_DEATH_SOUND_FILE = "PlayerLose.wav";
	public static final String ENEMY_DEATH_SOUND_FILE = "EnemyDeath.wav";
	public static final String WAVE_END_SOUND_FILE = "WaveEnd.wav";


	String getBackgroundMusicFilePath();
	String getPlayerShootSoundFilePath();
	String getEnemyShootSoundFilePath();
	String getPlayerDeathSoundFilePath();
	String getEnemyDeathSoundFilePath();
	String getWaveEndSoundFilePath();

	public void playBackgroundMusic();
	public void playPlayerShootSound();
	public void playEnemyShootSound();
	public void playPlayerDeathSound();
	public void playEnemyDeathSound();
	public void playWaveEndSound();
	public void stopBackgroundMusic();
}
