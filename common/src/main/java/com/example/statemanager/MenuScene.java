package com.example.statemanager;
import com.codename1.ui.Image;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.example.handler.MouseHandler;
import com.example.myapp.Game;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class MenuScene extends Scene{
    MouseHandler mh;
    Image menuImage = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/menuScreen.png");
    Image[] playButton = new Image[2]; //array to hold playbutton 
    int buttonSprite = 0; //which spirte to show
    
    //play button things
    public static int playButtonWidth = (int) (400 * Constants.WIDTH_SCALE);
    public static int playButtonHeight = (int)(100 * Constants.HEIGHT_SCALE);
    public static int playButtonX = (int) (((Constants.DEVICE_WIDTH)/2) - (playButtonWidth/2));
    public static int playButtonY =  (int) (((Constants.DEVICE_HEIGHT/2)) - (playButtonHeight/2));

    public MenuScene(MouseHandler mh) {
        this.mh = mh;
        initializePlayButton();
    }

    public void initializePlayButton() {
        Image img = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/playButton.png");
        for (int i = 0; i < playButton.length; i++) {
            playButton[i] = (Image) img.subImage(100 * i, 0, 100, 50, false);
        }
    }

    public static void touchMovement(int x, int y) {
        //track if mouse is in bounds of play button
        if (x > playButtonX && x < playButtonX + playButtonWidth && y > playButtonY && y < playButtonY + playButtonHeight) {
            Game.game.changeState(Constants.SCENE_PLAYING); //change scene if play button clicked
            Game.game.sceneNum = 1;
        } else {
            //buttonSprite = 0; //otherwise, it will stay the default button 
        }
    }

    @Override
    public void update() {
        Game.game.getVolumeButton().update();
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(menuImage, 0, 0, (int) (Constants.GAME_WIDTH * Constants.WIDTH_SCALE + 100), (int) (Constants.GAME_HEIGHT * Constants.HEIGHT_SCALE + 100)); //display start screen with play button
        g.drawImage(playButton[buttonSprite], playButtonX, playButtonY, playButtonWidth, playButtonHeight);
        Font defaultFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(defaultFont); //get load font glitch to pass in beginning
        g.drawString("", 0, 0);

        Game.game.getVolumeButton().draw(g); //draw volumebutton
    }
    
}