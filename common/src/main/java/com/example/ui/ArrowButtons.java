package com.example.ui;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class ArrowButtons {
Image arrows  = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/arrowControl.png"); //arrow image

//dimensions 
public int width = (int) (200 * Constants.WIDTH_SCALE);
public int height = (int) (150 * Constants.HEIGHT_SCALE);
public int xPos = 20;
public int yPos = (int) (Constants.DEVICE_HEIGHT) - (int) (height);
public float imageWidthScale = width/32;
public float imageHeightScale = height/24;

public ArrowButtons() {
    System.out.println(height);
}

public void draw(Graphics g) {
    g.drawImage(arrows, xPos, yPos, width, height);
    g.drawRect((int) (10 * imageWidthScale + xPos), yPos, (int) (12 * imageWidthScale), (int) (13 * imageHeightScale));
}
}
