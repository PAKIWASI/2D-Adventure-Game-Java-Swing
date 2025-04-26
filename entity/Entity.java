package entity;

import java.awt.image.BufferedImage;

public abstract class Entity
{
    protected int x, y; 
    protected int speed;

    protected BufferedImage r1, r2, l1, l2, d1, d2, u1, u2;
    protected Direction direction;

    protected int spriteCount = 0;
    protected int spriteNum = 1;    // 1 or 2
    
    
}
