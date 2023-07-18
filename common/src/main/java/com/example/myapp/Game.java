package com.example.myapp;

import com.codename1.ui.Graphics;
import com.example.audio.AudioPlayer;
import com.example.entity.Ducky;
import com.example.statemanager.DeathScene;
import com.example.statemanager.MenuScene;
import com.example.statemanager.PlayingScene;
import com.example.statemanager.Scene;
import com.example.ui.VolumeButton;
import com.example.util.Constants;

public class Game implements Runnable {
    public static Game game = null;
    Thread GameThread = new Thread(this);
    GameContainer panel = new GameContainer(this);
    GameForm frame = new GameForm(panel);

    // ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("res/duckIcon.png")); //for image icon

    Ducky duck = new Ducky(panel.kh, 100, 200, 40, 40);

    Scene currentScene;
    int sceneNum = Constants.SCENE_MENU; //controls which scene we are on
    
    public AudioPlayer audioPlayer = new AudioPlayer(); //audio player 
    public VolumeButton volumeButton = new VolumeButton(panel.mh);

    int i = 0;

    public Game() {
        changeState(sceneNum);
       // frame.add(panel);
        //frame.pack();
        //frame.setIconImage(logo.getImage());
        frame.setVisible(true);
    }
    //singleton game panel
    public static Game getGame() {
        if (Game.game == null) {
            Game.game = new Game();
        }
        return game;
    }
    public GameContainer getPanel() {
        return this.panel;
    }
    public Ducky getDucky() {
        return this.duck;
    }
    public AudioPlayer getAudioPlayer() {
        return this.audioPlayer;
    }
    public VolumeButton getVolumeButton() {
        return this.volumeButton;
    }

    //start game
    public void startGameThread() {
        GameThread.start();
    }

    //get rid of lost focus bug
    public void windowFocusLost() {
        duck.resetDir();
    }

    //change state method, creates new scene
    public void changeState(int sceneNum) {
        switch (sceneNum) {
            case Constants.SCENE_MENU: 
                currentScene = new MenuScene(panel.mh);
                //audioPlayer.playSong(AudioPlayer.MENU_SONG);
                break;
            case Constants.SCENE_PLAYING:
                currentScene = new PlayingScene(duck);
                //audioPlayer.playSong(AudioPlayer.PLAYING_SONG);
                break;
            case Constants.SCENE_DEATH: 
                currentScene = new DeathScene(panel.mh);
                //audioPlayer.playSong(AudioPlayer.HIGHSCORE_SONG);
                break;
            default:
                currentScene = null;
                break;
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/Constants.FPS; //get updateIntervals by dividing 1 second by desire updateInterval
        double updateInterval = 1000000000/Constants.UPS;
        double deltaT = 0; 
        double deltaU = 0;
        long lastTime = System.currentTimeMillis() * (1000000);
        long currentTime;  

        while (GameThread != null) {
            currentTime = System.currentTimeMillis() * (1000000);
            deltaT += (currentTime - lastTime) / drawInterval;
            deltaU += (currentTime - lastTime) / updateInterval;
            lastTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }
            if (deltaT >= 1) {
                panel.repaint();
                deltaT--;
            }
        }
    }

    //all rendering and updating stems from here 
    public void update() {
        currentScene.update();
        i++;
        if (i == 100) {
            changeState(1);
        }
    }
    public void draw(Graphics g) {
        currentScene.draw(g);
    }      
}