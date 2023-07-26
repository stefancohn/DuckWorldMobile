package com.example.util;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.ui.CN;
import com.codename1.ui.Image;

public class LoadSave {
    public static final String DUCKY_ATLAS = "/duckySprite.png";
    public static final String LEVEL_ATLAS = "/mapSprite.png";

    public static final String START_LEVEL = "/levelOne.png";
    public static final String OBSTACLE_SEQUENCES = "/levelSequences.png";

    //method to pull Image from file 
    public static Image getSpriteAtlas(String file) {
        Image img = null;
        try {
            InputStream is = CN.getResourceAsStream(file);
            img = Image.createImage(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    //grabs the Red value of each tile of level map and rerturns it as 
    //a 2d array
    public static int[][] getLevelDataRed(String file) {
        Image img = getSpriteAtlas(file);
        int[][] levelData = new int[img.getHeight()] [img.getWidth()];
        int[] rgbData = img.getRGB();

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                int rgb = rgbData[i * img.getWidth() + j];
                int red = (rgb >> 16) & 0xFF;

                int value = red;
                if (value >= 6) {
                    value = 4;
                }
                levelData[i][j] = value;
            }
        }
        return levelData;
    }
    //same method as above but works with images instead of files
    public static int[][] getLevelDataRedImg(Image img) {
        int[][] levelData = new int[img.getHeight()][img.getWidth()];
        int[] rgbData = img.getRGB();

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                int rgb = rgbData[i * img.getWidth() + j];
                int red = (rgb >> 16) & 0xFF;

                int value = red;
                if (value >= 6) {
                    value = 4;
                }
                levelData[i][j] = value;
            }
        }
        return levelData;
        }

    //retrieves blue num of RGB for enemy spawn
    public static int[][] getLevelDataBlue(String file) {
        Image img = getSpriteAtlas(file);
        int[][] levelData = new int[img.getHeight()] [img.getWidth()];

        int[] rgbData = img.getRGB();   
    
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                int rgb = rgbData[i * img.getWidth() + j];
                int blue = rgb & 0xFF;

                int value = (blue != 1) ? 0 : blue;
                levelData[i][j] = value;
                }
            }
        return levelData;
        }
    }