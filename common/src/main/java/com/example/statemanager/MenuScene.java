package com.example.statemanager;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.example.handler.MouseHandler;
import com.example.myapp.Game;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class MenuScene extends Scene{
    MouseHandler mh;
    EncodedImage menuImage = LoadSave.getSpriteAtlas("/res/menuScreen.png");
    EncodedImage[] playButton = new EncodedImage[2]; //array to hold playbutton 
    int buttonSprite = 0; //which spirte to show

    public MenuScene(MouseHandler mh) {
        this.mh = mh;
        initializePlayButton();
    }

    public void initializePlayButton() {
        EncodedImage img = LoadSave.getSpriteAtlas("/res/playButton.png");
        for (int i = 0; i < playButton.length; i++) {
            playButton[i] = (EncodedImage) img.subImage(100 * i, 0, 100, 50, false);
        }
    }

    public void mouseMovement() {
        //track if mouse is in bounds of play button
        if (mh.x > (Constants.GAME_WIDTH/2) - 200 && 
        mh.x < (((Constants.GAME_WIDTH/2) - 200) + 400) && mh.y > 200
        && mh.y < 300) {
            buttonSprite = 1; //change button to "selected" if it is in bounds
            if (mh.clicked) {
                Game.game.changeState(Constants.SCENE_PLAYING); //change scene if play button clicked
            }
        } else {
            buttonSprite = 0; //otherwise, it will stay the default button 
        }
    }

    @Override
    public void update() {
        mouseMovement();
        Game.game.getVolumeButton().update();
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(menuImage, 0, 0); //display start screen with play button
        g.drawImage(playButton[buttonSprite], (Constants.GAME_WIDTH/2) - 200, 200, 400, 100);
        Font defaultFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(defaultFont); //get load font glitch to pass in beginning
        g.drawString("", 0, 0);

        Game.game.getVolumeButton().draw(g); //draw volumebutton
    }
    
}