package com.example.ui;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import com.example.entity.Ducky;
import com.example.handler.MouseHandler;
import com.example.myapp.Game;
import com.example.statemanager.PlayingScene;
import com.example.util.Bounds;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class PauseOverlay {
    MouseHandler mh;
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

    public PauseOverlay() {
        initialzePauseOverlay();
        initializePauseButtons();
    }

    public void initialzePauseOverlay() {
        pauseOverlay = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/pauseScreen.png");
    }

    public void initializePauseButtons() {
        Image img = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/pauseButtons.png");
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
        }
        //quit button function and bounds
        if (Bounds.checkBounds(x, y, xPlacementForButtons, quitButtonY, buttonWidth, buttonHeight)) {
            quitButtonShown = 1;
        }
    }
    public void touchReleased(int x, int y) {
        if (Bounds.checkBounds(x, y, xPlacementForButtons, playButtonY, buttonWidth, buttonHeight)) {
            Ducky.kh.pause = false;
            PlayingScene.unpaused = true;
            playButtonShown = 0;
        }
        if (Bounds.checkBounds(x, y, xPlacementForButtons, quitButtonY, buttonWidth, buttonHeight)) {
            Game.game.getDucky().defaultDucky();
            Game.game.changeState(Constants.SCENE_MENU);
            quitButtonShown = 0;
        }
        if (Bounds.checkBounds(x, y, Ducky.kh.arrowButtons.pauseButtonX, Ducky.kh.arrowButtons.pauseButtonY, Ducky.kh.arrowButtons.arrowWidth, Ducky.kh.arrowButtons.arrowHeight)) {
            Ducky.kh.arrowButtons.pauseButtonSprite = 0;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(pauseOverlay, xPlacementForOverlay, yPlacementForOverlay, overlayWidth, overlayHeight);
        g.drawImage(pauseButtons[0][playButtonShown], xPlacementForButtons, playButtonY, buttonWidth, buttonHeight);
        g.drawImage(pauseButtons[1][quitButtonShown], xPlacementForButtons, quitButtonY, buttonWidth, buttonHeight);
    }
}