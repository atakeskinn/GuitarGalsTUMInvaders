package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class SoundManager implements SoundManagerInterface{

    private MediaPlayer mediaPlayer;
    private boolean playingBackgroundMusic;
    //public boolean playerShootSoundPlayed = false;

    /**
     * Constructor, gets the media files from resources and sets the boolean
     * playingBackgroundMusic false, which means that after game startup no
     * music is being played.
     */
    public SoundManager() {
        this.playingBackgroundMusic = false;
    }

    @Override
    public String getBackgroundMusicFilePath() {
        return BACKGROUND_MUSIC_FILE;
    }

    @Override
    public String getPlayerShootSoundFilePath() {
        return PLAYER_SHOOT_SOUND_FILE;
    }

    @Override
    public String getEnemyShootSoundFilePath() {
        return ENEMY_SHOOT_SOUND_FILE;
    }

    @Override
    public String getPlayerDeathSoundFilePath() {
        return PLAYER_DEATH_SOUND_FILE;
    }

    @Override
    public String getEnemyDeathSoundFilePath() {
        return ENEMY_DEATH_SOUND_FILE;
    }

    @Override
    public String getWaveEndSoundFilePath() {
        return WAVE_END_SOUND_FILE;
    }

    /**
     * Checks if no music is currently running by checking the value of the
     * boolean playingBackgroundMusic. Starts playing the background music in a
     * new thread.
     */
    public void playBackgroundMusic() {
        if (!this.playingBackgroundMusic) {
            this.playingBackgroundMusic = true;
            System.out.println("Going to try and load bg music file");

            this.mediaPlayer = new MediaPlayer(loadAudioFile(getBackgroundMusicFilePath()));
            this.mediaPlayer.setAutoPlay(true);
            // Loop for the main music sound:
            this.mediaPlayer.setOnEndOfMedia(() -> SoundManager.this.mediaPlayer.seek(Duration.ZERO));
            System.out.println("Started to play background music");
            this.mediaPlayer.setVolume(0.05);;
            this.mediaPlayer.play();
        }
    }

    @Override
    public void playPlayerShootSound() {
        MediaPlayer sound = new MediaPlayer(loadAudioFile(getPlayerShootSoundFilePath()));
        sound.setVolume(0.1);
        sound.play();
        //this.playerShootSoundPlayed = true;
    }

    @Override
    public void playEnemyShootSound() {
        MediaPlayer sound = new MediaPlayer(loadAudioFile(getEnemyShootSoundFilePath()));
        sound.play();
    }

    @Override
    public void playPlayerDeathSound() {
        MediaPlayer sound = new MediaPlayer(loadAudioFile(getPlayerDeathSoundFilePath()));
        sound.play();
    }

    @Override
    public void playEnemyDeathSound() {
        MediaPlayer sound = new MediaPlayer(loadAudioFile(getEnemyDeathSoundFilePath()));
        sound.setVolume(0.1);
        sound.play();
    }

    @Override
    public void playWaveEndSound() {
        MediaPlayer sound = new MediaPlayer(loadAudioFile(getWaveEndSoundFilePath()));
        sound.play();
    }

    private Media loadAudioFile(String fileName) {
        URL musicSourceUrl = SoundManager.class.getClassLoader().getResource(fileName);
        if(musicSourceUrl == null) {
            throw new RuntimeException("Please ensure that your resources folder contains the appropriate files for this exercise.");
        }
        String musicSource = musicSourceUrl.toString();
        return new Media(musicSource);
    }

    /**
     * Checks if music is currently running and stops it if so
     */
    public void stopBackgroundMusic() {
        if (this.playingBackgroundMusic) {
            this.playingBackgroundMusic = false;
            this.mediaPlayer.stop();
        }
    }

    /**
     * Checks if the background music is playing by returning the boolean
     * playingBackgroundMusic. This boolean is initially false after the game
     * startup and set to true in the playBackgroundMusic() method.
     *
     * @return true if background music is playing
     * @return false if background music is not playing
     */
    public boolean isPlayingBackgroundMusic() {
        return playingBackgroundMusic;
    }

    /*@Override
    public void playCrashSound() {
        // define new MediaPlayer variable for Bang Sound
        MediaPlayer mediaPlayerBang = new MediaPlayer(loadAudioFile(getCrashSoundFilePath()));
        mediaPlayerBang.play();
        // set boolean Variable to true
        this.crashSoundPlayed = true;
    }

    @Override
    public boolean getCrashSoundPlayed() {
        return this.crashSoundPlayed;
    }*/

}
