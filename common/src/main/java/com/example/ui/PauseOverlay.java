package com.example.ui;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import com.example.entity.Ducky;
import com.example.myapp.Game;
import com.example.statemanager.PlayingScene;
import com.example.util.Bounds;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class PauseOverlay {
    Image pauseOverlay;

    int overlayWidth = 650;
    int overlayHeight = 850;
    int xPlacementForOverlay = (Constants.DEVICE_WIDTH/2) - (overlayWidth/2);
    int yPlacementForOverlay =  (Constants.DEVICE_HEIGHT/2) - (overlayHeight/2);

    //buttons stuffs
    Image[][] pauseButtons = new Image[2][2];
    int buttonWidth = 350;
    int buttonHeight = 150;
    int xPlacementForButtons = ((xPlacementForOverlay + overlayWidth) - (overlayWidth/2)) - (buttonWidth/2);
    int playButtonY = yPlacementForOverlay + (int) (overlayHeight * .25);
    int quitButtonY = yPlacementForOverlay + (int) (overlayHeight * .6);
    int playButtonShown = 0;
    int quitButtonShown = 0;

    Boolean resumePressed = false;
    Boolean quitPressed = false;

    public PauseOverlay() {
        initialzePauseOverlay();
        initializePauseButtons();
    }

    public void initialzePauseOverlay() {
        pauseOverlay = LoadSave.getSpriteAtlas("/pauseScreen.png");
    }

    public void initializePauseButtons() {
        Image img = LoadSave.getSpriteAtlas("/pauseButtons.png");
        for (int i = 0; i < pauseButtons.length; i++) {
            for (int j = 0; j < pauseButtons[i].length; j++) {
                pauseButtons[i][j] = img.subImage(j * 200, i * 50, 200, 50, false);
            }
        }
    }

    public void touchMovement(int x, int y) {
        //resume button function and bounds
        if (Bounds.checkBounds(x, y, xPlacementForButtons, playButtonY, buttonWidth, buttonHeight)) {
            playButtonShown = 1;
            resumePressed = true;
        }
        //quit button function and bounds
        if (Bounds.checkBounds(x, y, xPlacementForButtons, quitButtonY, buttonWidth, buttonHeight)) {
            quitButtonShown = 1;
            quitPressed = true;
        }
    }
    public void touchReleased(int x, int y) {
        //resume button function and bounds
        if (resumePressed) {
            Ducky.kh.pause = false;
            PlayingScene.unpaused = true;
            playButtonShown = 0;
            resumePressed = false;
        }
        //quit button function and bounds
        if (quitPressed) {
            Game.game.getDucky().defaultDucky();
            Game.game.changeState(Constants.SCENE_MENU);
            quitButtonShown = 0;
            quitPressed = false; 
        }
        if (Bounds.checkBounds(x, y, Ducky.kh.arrowButtons.pauseButtonX, Ducky.kh.arrowButtons.pauseButtonY, Ducky.kh.arrowButtons.arrowWidth, Ducky.kh.arrowButtons.arrowHeight)) {
            Ducky.kh.arrowButtons.pauseButtonSprite = 0; //make pause button lighten after press
        }
    }

    public void draw(Graphics g) {
        g.drawImage(pauseOverlay, xPlacementForOverlay, yPlacementForOverlay, overlayWidth, overlayHeight);
        g.drawImage(pauseButtons[0][playButtonShown], xPlacementForButtons, playButtonY, buttonWidth, buttonHeight);
        g.drawImage(pauseButtons[1][quitButtonShown], xPlacementForButtons, quitButtonY, buttonWidth, buttonHeight);
    }
}