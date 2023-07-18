package com.example.levels;
import com.codename1.ui.Image;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Graphics;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class LevelManager {
    private Image[] levelSprite = new Image[6];  //holds all the blocks from sprite sheet to build level 
    private Image[] obstacleSprites = new Image[Constants.AMOUNT_OF_PATTERNS]; //holds all random level patterns 
    private Image background = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/background.png");

    Level mainLevel;
    Level[] obstacleSequences = new Level[Constants.AMOUNT_OF_PATTERNS];

    public LevelManager(){
        importLevelSprite();
        //initializes new level by using LoadSave getRed method to grab level
        //data of start level to feed into level constructor
        mainLevel = new Level(LoadSave.getLevelDataRed(LoadSave.START_LEVEL));
        importObstacleSequences();
    }

    //grabs each block of map sprite into an array so map can be built
    public void importLevelSprite() {
        Image img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        for (int i = 0; i < levelSprite.length; i++) {
            levelSprite[i] = (Image) img.subImage(i * 16, 0, 16, 16, false);
        }
    }

    //creates levelDatas for obstacles sprites
    public void importObstacleSequences() {
        Image img = LoadSave.getSpriteAtlas(LoadSave.OBSTACLE_SEQUENCES); //loads gimp file into bufferedimg
        for (int j = 0; j < (img.getWidth()/Constants.TILES_IN_WIDTH); j++) {
            obstacleSprites[j] = (Image) img.subImage(j * 50, 0, 50, 30, false); //fills in array of bufferedimg with subimages of obstacle sequences
        }
        for (int i = 0; i < obstacleSprites.length; i ++) {
            obstacleSequences[i] = new Level(LoadSave.getLevelDataRedImg(obstacleSprites[i])); //retrieves leveldata RGB
        }
    }

    //method to transplant randomly selected sequence from obstacleSequences to mainLevel
    //iterates through mainLevel data and replaces it with obstacleSequences data
    public void transformMainLevel(int xOffset, int obstacleCounter, int pattern) {
        int width = mainLevel.getLevelData()[0].length - 1; //get width
        for (int i = 0; i < mainLevel.getLevelData().length; i++) { //iterate through mainLevel level data (rows)
            for (int j = width, k =0; j > (mainLevel.getLevelData()[i].length - 1) - xOffset; j--, k++){ //iterate through columns starting from 50
                mainLevel.getLevelData()[i][j] = obstacleSequences[pattern].getLevelData()[i][k + obstacleCounter]; //
            }
        }
    }

    public Level getCurrentLevel() {
        return mainLevel;
    }

    public void update() {
    }
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0);
        for (int i = 0; i < Constants.TILES_IN_HEIGHT; i++) 
            for (int j = 0; j < Constants.TILES_IN_WIDTH; j++) {
                int index = mainLevel.getSpriteIndex(i, j);
                if (index != 4) {
                //these are drawn to size because the level builder sprites are 16*16, no size def needed
                //does not draw any black squares in the case that a background is to be drawn
                    g.drawImage(levelSprite[index], j * Constants.TILES_SIZE_DEF, i * Constants.TILES_SIZE_DEF, 16, 16);
                }
        }
    }
}