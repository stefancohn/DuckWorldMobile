package com.example.myapp;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.example.handler.KeyHandler;

public class GameContainer extends Container { //JPanel equivalent
    public KeyHandler kh = new KeyHandler();
    private Game game;

    public GameContainer(Game game) {
        super(new BorderLayout());
        this.setUIID("GameContainer");
        this.setFocusable(true);
        //this.setScrollable(false);

        /*this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.addPointerPressedListener(mh);
        this.addPointerDraggedListener(mh);
        this.addPointerReleasedListener(mh);*/
        
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public KeyHandler getKeyHandler() {
        return kh;
    }

    public void update() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        game.draw(g);
    }
}
