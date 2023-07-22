package com.example.ui;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class VolumeButton {
    Boolean toggle = false;
    Boolean stopClick = true;

    Image[][] volumeButtons = new Image[2][2]; //holds sprites
    int volumeHovered = 0; //update this int to display greyed out icon when hovered
    int volumeSprite = 0; //update this to show either the muted or default icon 

    public VolumeButton() {
        loadButton();
    }

    private void loadButton() { //put buttons into array 
        Image img = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/volumeToggle.png");
        for (int i = 0; i < volumeButtons.length; i++) {
            for (int j = 0; j < volumeButtons[i].length; j++) {
                volumeButtons[i][j] = (Image) img.subImage(j * 230, i * 170, 230, 170, false);
            }
        }
    }

    private void touchMovement() { //track mousemovement to properly update volume icon and mute the game when selected
        
    }

    private void iconMuted() {
        volumeSprite = 1;
        //Game.game.getAudioPlayer().setVolume(0f);
        toggle = true;
        stopClick = false;
    }
    private void iconUnmuted() {
        volumeSprite = 0;
        //Game.game.getAudioPlayer().setVolume(0.8f);
        toggle = false;
        stopClick = false;
    }

    public void update(){
    }

    public void draw(Graphics g){
        //draw icon
        g.drawImage(volumeButtons[volumeSprite][volumeHovered], 0, Constants.GAME_HEIGHT - 50, 50, 50);
    }
}
