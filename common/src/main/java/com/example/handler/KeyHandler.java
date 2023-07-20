package com.example.handler;

import com.codename1.ui.Graphics;
import com.example.statemanager.PlayingScene;
import com.example.ui.ArrowButtons;

public class KeyHandler {
    //direction booleans
    public Boolean upPressed = false;
    public Boolean downPressed = false;
    public Boolean leftPressed = false;
    public Boolean rightPressed = false;
    public Boolean spacePressed = false;
    public Boolean pause = false;
    public String direction = "";

    public ArrowButtons arrowButtons = new ArrowButtons(); 

    public void touchMovement(int x, int y) {
        if (x > arrowButtons.xPos && x < arrowButtons.xPos + arrowButtons.width && y > arrowButtons.yPos && y < arrowButtons.yPos + arrowButtons.height) {
            upPressed = true;
            direction = "up";
        }
    }
        /*if (i == KeyEvent.VK_S) {
            downPressed = true;
            direction = "down";
        }
        if (i == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (i == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (i == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (i == KeyEvent.VK_ESCAPE) {
            //to make sure the restart counter happens whne the game is unpaused via escape
            if (pause) {
                pause = !pause;
                PlayingScene.unpaused = true;
            } else {
            pause = !pause; 
        }
        }
    } 

    */
    public void keyReleased(int x, int y) {
        if (x > arrowButtons.xPos && x < arrowButtons.xPos + arrowButtons.width && y > arrowButtons.yPos && y < arrowButtons.yPos + arrowButtons.height) {
            //upPressed = false;
            direction = "";
        } }/* 
        if (i == KeyEvent.VK_S) {
            downPressed = false;
            direction = "";
        }
        if (i == KeyEvent.VK_D) {
            rightPressed = false;
            direction = "";
        }
        if (i == KeyEvent.VK_A) {
            leftPressed = false;
            direction = "";
        }
    } */
    public Boolean getUpPres() { return upPressed; }
    public Boolean getDownPres() { return downPressed; }
    public Boolean getRightPres() { return rightPressed; }
    public Boolean getLeftPres() { return leftPressed; }
    public Boolean getSpacePres() {return spacePressed; }
    public Boolean getPause() {return pause;}

    public void draw(Graphics g) {
        arrowButtons.draw(g);
    }
}