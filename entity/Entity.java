package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    protected int worldX, worldY; // entity of player on world map , used for player movement around the world map
    protected int speed;

    protected BufferedImage r1, r2, l1, l2, d1, d2, u1, u2;
    protected Direction direction;

    protected int spriteCount = 0;
    protected int spriteNum = 1;    // 1 or 2
    
    //protected boolean collition = false;
    //protected Rectangle collitionArea; // rect with x, y position, width and height for player collition detection

   // public Rectangle getCollitionArea() { return collitionArea; }
   // public void setCollition(boolean collition) { this.collition = collition; }

    
    public Direction getDirection() { return direction; }
    public int getWorldX() { return worldX; }
    public int getWorldY() { return worldY; }
    public int getSpeed() { return speed; }
}
