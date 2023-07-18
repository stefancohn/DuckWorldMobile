package com.example.statemanager;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;

import com.example.handler.MouseHandler;
import com.example.ui.DeathSceneOverlay;
import com.example.util.LoadSave;

public class DeathScene extends Scene {
    MouseHandler mh;
    DeathSceneOverlay overlay;

    EncodedImage background = LoadSave.getSpriteAtlas("/res/backgroundDeath.png");

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
        g.drawImage(background, 0, 0);
        overlay.draw(g);
    }
}