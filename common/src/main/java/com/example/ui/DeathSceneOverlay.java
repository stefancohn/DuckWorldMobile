package com.example.ui;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Image;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.example.myapp.Game;
import com.example.util.Bounds;
import com.example.util.Constants;
import com.example.util.LoadSave;
import com.example.util.SaveScores;

public class DeathSceneOverlay {
    Image[][] deathSceneButtons = new Image[1][2]; //holds sprites 

    //button variables
    int buttonWidth = 750;
    int buttonHeight = 250; 
    int buttonPlacementX = (int) ((Constants.DEVICE_WIDTH/1.4) - (buttonWidth/2));
    int playAgainY = (int) (Constants.DEVICE_HEIGHT * .45);

    //variables to keep track which butotn sprite to show 
    int playAgainButtonSprite = 0;
    Boolean playButtonPressed = false;

    //variables for highscore placement
    int hsX = (int) (Constants.DEVICE_WIDTH * .20);
    int hsY = (int) (Constants.DEVICE_HEIGHT * .225);

    VolumeButton volumeButton = new VolumeButton();

    public DeathSceneOverlay() { 
        loadButtons();
    }

    public void loadButtons() { //get button from image and place it into array
        Image img = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/deathScreenButtons.png");
        for (int i = 0; i < deathSceneButtons.length; i++) {
            for (int j = 0; j < deathSceneButtons[i].length; j++) {
                deathSceneButtons[i][j] = (Image) img.subImage(j * 200, i * 50, 200, 50, false);
            }
        }
    }

    public void touchMovement(int x, int y) { //track touch movement to switch images when touch
        //for play again button
        if (Bounds.checkBounds(x, y, buttonPlacementX, playAgainY, buttonWidth, buttonHeight)) {
            playAgainButtonSprite = 1;
            playButtonPressed = true;
        }
    }
    public void releaseMovement(int x, int y) {
        if (x > 0 && y > 0 && playButtonPressed) { //actions take place upon relase
            playAgainButtonSprite = 0;
            Game.game.getDucky().defaultDucky();
            Game.game.changeState(Constants.SCENE_MENU);
            playButtonPressed = false;
        }

    }

    public void update() {
        Game.game.getVolumeButton().update();
    }
    public void draw(Graphics g) {
        g.drawImage(deathSceneButtons[0][playAgainButtonSprite], buttonPlacementX, playAgainY, buttonWidth, buttonHeight); //draw play again button
        Game.game.getVolumeButton().draw(g); //draw volume button

        //draw highscores
        Font defaultFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        g.setFont(defaultFont);
        g.drawString("HIGHSCORES", hsX, hsY);
        for (int i = 0; i < SaveScores.highscores.size(); i++) {
            g.drawString((i + 1) + ") " + SaveScores.highscores.get(i).toString(), hsX, 375 + i * hsY);
        }
    }

}