package com.example.myapp;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.example.handler.KeyHandler;
import com.example.handler.MouseHandler;

public class GameContainer extends Container { //JPanel equivalent
    public KeyHandler kh = new KeyHandler();
    MouseHandler mh = new MouseHandler();
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

    public MouseHandler getMouseHandler() {
        return mh;
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
