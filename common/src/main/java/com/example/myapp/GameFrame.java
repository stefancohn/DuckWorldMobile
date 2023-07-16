package com.example.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

public class GameFrame extends Form {

    public GameFrame(GameContainer panel) {
        super(new BorderLayout());
        this.setUIID("GameForm");
        this.setScrollable(false);
        //this.setSwipeActivated(false);
        this.setBackCommand(null);
        this.setTitle("Duck World");
    }
}