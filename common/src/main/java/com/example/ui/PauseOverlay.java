package com.example.ui;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import com.example.handler.MouseHandler;
import com.example.myapp.Game;
import com.example.statemanager.PlayingScene;
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

    public PauseOverlay(MouseHandler mh) {
        this.mh = mh;
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

    public void mouseMovement() {
        //resume button function and bounds
        if (mh.x > xPlacementForButtons && mh.x < xPlacementForButtons + buttonWidth
        && mh.y > playButtonY && mh.y < playButtonY + buttonHeight) {
            playButtonShown = 1;
            if (mh.clicked) {
                Game.getGame().getPanel().kh.pause = false;
                PlayingScene.unpaused = true;
            }
        } else {
            playButtonShown = 0;
        }

        //quit button function and bounds
        if (mh.x > xPlacementForButtons && mh.x < xPlacementForButtons + buttonWidth
        && mh.y > quitButtonY && mh.y < quitButtonY + buttonHeight) {
            quitButtonShown = 1;
            if (mh.clicked) {
                System.exit(0);
            }
        } else {
            quitButtonShown = 0;
        }
    }

    public void update() {
        mouseMovement();
    }
    public void draw(Graphics g) {
        g.drawImage(pauseOverlay, xPlacementForOverlay, yPlacementForOverlay, overlayWidth, overlayHeight);
        g.drawImage(pauseButtons[0][playButtonShown], xPlacementForButtons, playButtonY, buttonWidth, buttonHeight);
        g.drawImage(pauseButtons[1][quitButtonShown], xPlacementForButtons, quitButtonY, buttonWidth, buttonHeight);
    }
}