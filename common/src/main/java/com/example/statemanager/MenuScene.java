package com.example.statemanager;
import com.codename1.ui.Graphics;
import com.example.myapp.Game;
import com.example.ui.MenuSceneOverlay;

public class MenuScene extends Scene{
    public static MenuSceneOverlay menuSceneOverlay = new MenuSceneOverlay();

    public MenuScene() {
    }

    @Override
    public void update() {
        Game.game.getVolumeButton().update();
    }
    @Override
    public void draw(Graphics g) {
        menuSceneOverlay.draw(g);
        Game.game.getVolumeButton().draw(g); //draw volumebutton
    }
    
}