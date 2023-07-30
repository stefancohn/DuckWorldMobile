package com.example.handler;

import com.codename1.ui.Graphics;
import com.example.statemanager.PlayingScene;
import com.example.ui.playingUI;
import com.example.util.Bounds;

public class KeyHandler {
    //direction booleans
    public Boolean upPressed = false;
    public Boolean downPressed = false;
    public Boolean leftPressed = false;
    public Boolean rightPressed = false;
    public Boolean spacePressed = false;
    public Boolean pause = false;
    public String direction = "";

    Boolean upTouched = false; 

    public playingUI arrowButtons = new playingUI(); 

    public void touchMovement(int x, int y) {
        if (Bounds.checkBounds(x, y, arrowButtons.upArrowX, arrowButtons.yPos, arrowButtons.arrowWidth, arrowButtons.arrowHeight)) {
            upPressed = true;
            direction = "up";
            arrowButtons.arrowSprite = 2;
            upTouched = true;
        } 
        if (Bounds.checkBounds(x, y, arrowButtons.xPos, arrowButtons.sideArrowY, arrowButtons.arrowWidth, arrowButtons.arrowHeight)) { 
            leftPressed = true;
            arrowButtons.arrowSprite = 1;
         } 
        if (Bounds.checkBounds(x, y, arrowButtons.rightArrowX, arrowButtons.sideArrowY, arrowButtons.arrowWidth, arrowButtons.arrowHeight)) {
            rightPressed = true;
            arrowButtons.arrowSprite = 3;
        }  
        if (Bounds.checkBounds(x, y, arrowButtons.shootButtonX, arrowButtons.shootButtonY, arrowButtons.arrowWidth, arrowButtons.arrowHeight)) {
            spacePressed = true;
            arrowButtons.shootButtonSprite = 1; 
        } 
        if (Bounds.checkBounds(x, y, arrowButtons.pauseButtonX, arrowButtons.pauseButtonY, arrowButtons.arrowWidth, arrowButtons.arrowHeight)) {
            arrowButtons.pauseButtonSprite = 1;
            //to make sure the restart counter happens whne the game is unpaused via escape
            if (pause) {
                pause = !pause;
                PlayingScene.unpaused = true;
            } else {
            pause = !pause; 
            }
        }
    } 

    
    public void keyReleased(int x, int y) {
        if (upTouched) {
            //upPressed = false;
            direction = "";
            arrowButtons.arrowSprite = 0;
            upTouched = false;
        } 
        if (rightPressed) {
            rightPressed = false;
            direction = "";
            arrowButtons.arrowSprite = 0;
        }
        if (leftPressed) { 
            leftPressed = false;
            direction = "";
            arrowButtons.arrowSprite = 0;
        }
        if (spacePressed) {
            arrowButtons.shootButtonSprite = 0; 
        }
    } 
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