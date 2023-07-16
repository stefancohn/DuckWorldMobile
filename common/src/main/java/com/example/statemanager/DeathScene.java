package com.example.statemanager;
import com.codename1.ui.Graphics;
import java.awt.image.BufferedImage;

import com.example.handler.MouseHandler;
import com.example.ui.DeathSceneOverlay;
import com.example.util.LoadSave;

public class DeathScene extends Scene {
    MouseHandler mh;
    DeathSceneOverlay overlay;

    BufferedImage background = LoadSave.getSpriteAtlas("/res/backgroundDeath.png");

    public DeathScene(MouseHandler mh) {
        this.mh = mh;
        overlay = new DeathSceneOverlay(mh);
    }

    @Override
    public void update() {
        overlay.update();
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        overlay.draw(g);
    }
}