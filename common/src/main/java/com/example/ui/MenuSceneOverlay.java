package com.example.ui;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.example.myapp.Game;
import com.example.util.Bounds;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class MenuSceneOverlay {
    Image menuImage = LoadSave.getSpriteAtlas("/menuScreen.png");
    Image[] playButton = new Image[2]; //array to hold playbutton 
    int buttonSprite = 0; //which spirte to show
    
    //play button things
    public int playButtonWidth = (int) (400 * Constants.WIDTH_SCALE);
    public int playButtonHeight = (int)(100 * Constants.HEIGHT_SCALE);
    public int playButtonX = (int) (((Constants.DEVICE_WIDTH)/2) - (playButtonWidth/2));
    public int playButtonY =  (int) (((Constants.DEVICE_HEIGHT/2)) - (playButtonHeight/2));

    public MenuSceneOverlay() {
        initializePlayButton();
    }

    public void initializePlayButton() {
        Image img = LoadSave.getSpriteAtlas("/playButton.png");
        for (int i = 0; i < playButton.length; i++) {
            playButton[i] = (Image) img.subImage(100 * i, 0, 100, 50, false);
        }
    }

    public void touchMovement(int x, int y) {
        if (Bounds.checkBounds(x, y, playButtonX, playButtonY, playButtonWidth, playButtonHeight)) {
            buttonSprite = 1;
        }
    }
    public void releaseMovement(int x, int y) {
        if (Bounds.checkBounds(x, y, playButtonX, playButtonY, playButtonWidth, playButtonHeight)) {
            Game.game.changeState(Constants.SCENE_PLAYING); //change scene if play button clicked
            buttonSprite = 0;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(menuImage, 0, 0, (int) (Constants.GAME_WIDTH * Constants.WIDTH_SCALE + 100), (int) (Constants.GAME_HEIGHT * Constants.HEIGHT_SCALE + 100)); //display start screen with play button
        g.drawImage(playButton[buttonSprite], playButtonX, playButtonY, playButtonWidth, playButtonHeight);
        Font defaultFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(defaultFont); //get load font glitch to pass in beginning
        g.drawString("", 0, 0);
    }
}
