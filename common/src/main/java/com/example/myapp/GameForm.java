package com.example.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

public class GameForm extends Form { //JPanel equivelant

    GameForm(GameContainer container) {
        super(new BorderLayout());
        this.setUIID("GameForm");
        this.setScrollable(false);
        //this.setSwipeActivated(false);
        this.setBackCommand(null);
        this.setTitle("Duck World");
        this.getContentPane().addComponent(BorderLayout.CENTER, container);
        this.show();
    }
}
