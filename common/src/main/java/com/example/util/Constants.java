package com.example.util;

import com.codename1.ui.Display;

public class Constants {
    //values to be used across whole game
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 750;
    public static final int FPS = 120;
    public static final int UPS = 120;
    public static final int DEVICE_WIDTH =  Display.getInstance().getDisplayWidth();
    public static final int DEVICE_HEIGHT = Display.getInstance().getDisplayHeight();

    public static final int TILES_SIZE_DEF = 16;
    public static final float SCALE = 1.0f;
    public static final int TILES_IN_WIDTH = 50;
    public static final int TILES_IN_HEIGHT = 30;
    public static final int TILES_SIZE = (int)(TILES_SIZE_DEF * SCALE);
    //800 x 480
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    //because this game is very dependent on this 50*30 tile 16 pixel block structre, we scale all the images by enlarging all them
    //by how much bigger the dimension are on mobile
    public static final float WIDTH_SCALE = (float) DEVICE_WIDTH / (float) GAME_WIDTH;
    public static final float HEIGHT_SCALE = (float) DEVICE_HEIGHT / (float) GAME_HEIGHT;

    public static final int GRAVITY = 3;
    public static final int DUCKY_SPEED = 2; 
    public static final int DUCKY_IDLE = 0;
    public static final int DUCKY_RIGHT = 1;
    public static final int DUCKY_LEFT = 2;
    public static final int DUCKY_ATTACK_RIGHT = 3; 
    public static final int DUCKY_ATTACK_LEFT = 4;
    public static final int DUCKY_DEATH = 5;

    public static final int SCENE_MENU = 0;
    public static final int SCENE_PLAYING = 1;
    public static final int SCENE_DEATH = 2;

    public static final int AMOUNT_OF_PATTERNS = 7;
    public static final int MOVE_SCREEN_RIGHT_LENGTH = 1;

    public static final int ENEMY_SPEED = 1;
}