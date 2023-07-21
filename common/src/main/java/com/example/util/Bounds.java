package com.example.util;

public class Bounds {
    public static Boolean checkBounds(int x, int y, int objX, int objY, int width, int height) {
        return (x > objX && x < objX + width && y > objY && y < objY + height);
    }
}
