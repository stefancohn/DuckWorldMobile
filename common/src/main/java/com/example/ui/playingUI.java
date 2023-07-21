package com.example.ui;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class playingUI {
Image arrows  = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/arrowControl.png"); //arrow image
Image[] shootButton = new Image[2]; 
Image[] pauseButton = new Image[2];

//dimensions for arrow sprite sheet
public int width = (int) (200 * Constants.WIDTH_SCALE);
public int height = (int) (150 * Constants.HEIGHT_SCALE);
public int xPos = 20;
public int yPos = (int) (Constants.DEVICE_HEIGHT) - (int) (height);

//these represent how much bigger the image is in-game to the root image 
public float imageWidthScale = width/32;
public float imageHeightScale = height/24;
public int arrowWidth = (int) (12 * imageWidthScale);
public int arrowHeight = (int) (13 * imageHeightScale);

public int upArrowX = (int) (10 * imageWidthScale + xPos);
public int sideArrowY = (int) (yPos + 14 * imageHeightScale);
public int rightArrowX = (int) (xPos + 19 * imageWidthScale);

public int shootButtonSprite = 0;
public int shootButtonY = Constants.DEVICE_HEIGHT - arrowHeight;
public int shootButtonX = Constants.DEVICE_WIDTH - arrowWidth - 20;

public int pauseButtonSprite = 0;
public int pauseButtonX = shootButtonX;
public int pauseButtonY = 20;

public playingUI() {
    loadShootButton();
    loadPauseButton();
}

private void loadPauseButton() {
    Image img = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/PauseButton.png");
    for (int i = 0; i < shootButton.length; i++) {
        pauseButton[i] = img.subImage(i * 20, 0, 20, 20, false);
    }
}

private void loadShootButton() {
    Image img = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/ShootButton.png");
    for (int i = 0; i < shootButton.length; i++) {
        shootButton[i] = img.subImage(i * 20, 0, 20, 20, false);
    }

}

public void draw(Graphics g) {
    g.drawImage(arrows, xPos, yPos, width, height);
    g.drawImage(shootButton[shootButtonSprite], shootButtonX, shootButtonY, arrowWidth, arrowHeight);
    g.drawImage(pauseButton[pauseButtonSprite], pauseButtonX, pauseButtonY, arrowWidth, arrowHeight);
}
}
