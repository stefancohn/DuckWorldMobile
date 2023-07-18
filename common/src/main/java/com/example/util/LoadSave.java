package com.example.util;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.EncodedImage;

public class LoadSave {
    public static final String DUCKY_ATLAS = "/res/duckySprite.png";
    public static final String LEVEL_ATLAS = "/res/mapSprite.png";

    public static final String START_LEVEL = "/res/levelOne.png";
    public static final String OBSTACLE_SEQUENCES = "/res/levelSequences.png";

    //method to pull EncodedImage from file 
    public static EncodedImage getSpriteAtlas(String file) {
        EncodedImage img = null;
        try {
            InputStream is = FileSystemStorage.getInstance().openInputStream(file);
            img = EncodedImage.create(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    //grabs the Red value of each tile of level map and rerturns it as 
    //a 2d array
    public static int[][] getLevelDataRed(String file) {
        EncodedImage img = getSpriteAtlas(file);
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
    public static int[][] getLevelDataRedImg(EncodedImage img) {
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
        EncodedImage img = getSpriteAtlas(file);
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