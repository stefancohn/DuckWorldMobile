package com.example.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.example.entity.Ducky;
import com.example.handler.KeyHandler;
import com.example.statemanager.MenuScene;
import com.example.statemanager.PlayingScene;
import com.example.util.Constants;

public class GameForm extends Form { //JPanel equivelant
    KeyHandler kh = Ducky.kh;
    GameForm(GameContainer container) {
        super(new BorderLayout());
        this.setScrollable(false);
        this.setBackCommand(null);
        this.getContentPane().addComponent(BorderLayout.CENTER, container);
        this.show();
        this.addPointerPressedListener(getClearCommand());
        this.addPointerReleasedListener(getClearCommand());
        this.addPointerDraggedListener(getClearCommand());
    }

    public void pointerPressed(int x, int y) {
        if (Game.game.sceneNum == Constants.SCENE_MENU) {
            MenuScene.touchMovement(x, y);
        } else if (Game.game.sceneNum == Constants.SCENE_PLAYING && !Ducky.kh.getPause()) { //IMPLEMENT IN SOME WAY && !Ducky.kh.getPause
            kh.touchMovement(x, y);
        } else if (Game.game.sceneNum == Constants.SCENE_PLAYING && Ducky.kh.getPause()) {
            PlayingScene.pauseScreen.touchMovement(x, y);
        }
    }

    public void pointerReleased(int x, int y) {
        if (Game.game.sceneNum == Constants.SCENE_PLAYING  && !Ducky.kh.getPause()) {
            kh.keyReleased(x, y);
        } else if (Game.game.sceneNum == Constants.SCENE_PLAYING && Ducky.kh.getPause()) {
            PlayingScene.pauseScreen.touchReleased(x, y);
        }
    }
}
