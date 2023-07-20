package com.example.entity;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import com.example.util.Collisions;
import com.example.util.Constants;
import com.example.util.LoadSave;

public class DuckyProjectile extends Entity {
    Image projectileImg; 

    int projectileSpeed = 4; 

    Boolean collided = false; //this boolean will make use in the enemymanager so that we can properly delete projectiles
    Boolean isRight;

    int[][] levelData;

    public DuckyProjectile(int x, int y, int width, int height, Boolean isRight, int[][] levelData) {
        super(x, y, width, height);
        initializeHitbox(x, y, width, height);
        loadProjectile();
        this.levelData = levelData;
        this.isRight = isRight;
    }

    private void loadProjectile() { //get spriteAtlas, get subimage
        projectileImg = LoadSave.getSpriteAtlas(FileSystemStorage.getInstance().getAppHomePath() + "res/marshmallow.png");
        projectileImg = (Image) projectileImg.subImage(0, 0, 16, 16, false);
    }

    public void constantMove() { //will keep going until there is a collision detected
        if (!collided && Collisions.canMoveHere(hitbox.x + projectileSpeed, hitbox.y, width, height, levelData) && isRight) {
            hitbox.x += projectileSpeed; //if is right, hasn't collided, and can move adds projectile speed
        } else if (!collided && Collisions.canMoveHere(hitbox.x - projectileSpeed, hitbox.y, width, height, levelData) && !isRight) {
            hitbox.x -= projectileSpeed; //if is left, hasn't collided, and can move subtracts projectile speed
        }
        else {
            hitbox.x = Collisions.getXposNextToWallRightMoving(hitbox);
            collided = true;
        }
    }

    public void update() {
        constantMove();
    }
    public void draw(Graphics g) {
        g.drawImage(projectileImg, (int) (hitbox.x * Constants.WIDTH_SCALE), (int) (hitbox.y * Constants.HEIGHT_SCALE), 
        (int) (width * Constants.DEVICE_WIDTH), (int) (height * Constants.DEVICE_HEIGHT));
    }
    
}