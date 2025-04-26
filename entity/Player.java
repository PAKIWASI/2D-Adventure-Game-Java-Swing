package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity
{
    private GamePanel gp;
    private KeyHandler keyH;


    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        setDefault();
        getPlayerImage();
    }

    public void update()  // gets called 60 times / sec (FPS)
    {
        
        if (keyH.upPressed() == true) 
        {
            direction = Direction.UP;
            y -= speed; 
        }

        if (keyH.downPressed() == true) 
        {
            direction = Direction.DOWN;
            y += speed;
        }   

        if (keyH.leftPressed() == true) 
        {
            direction = Direction.LEFT;
            x -= speed;
        }

        if (keyH.rightPressed() == true) 
        {
            direction = Direction.RIGHT;
            x += speed;
        }    

        if (keyH.upPressed() || keyH.downPressed() || keyH.rightPressed() || keyH.leftPressed()) // only update if key pressed
        {
            spriteCount++;
            if (spriteCount > 12)  // frames are updated every 12 iterations out of 60 / sec
            {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                
                spriteCount = 0;
            }

        }
    }

    public void draw(Graphics2D g2) // on-screen representation of player
    {
        BufferedImage image = null;

        switch(direction)
        {
            case UP: 
                if (spriteNum == 1)
                    image = u1;
                if (spriteNum == 2)
                    image = u2;
                break;
            case DOWN: 
                if (spriteNum == 1)
                    image = d1;
                if (spriteNum == 2)
                    image = d2;

                break;
            case LEFT: 
                if (spriteNum == 1)
                    image = l1;
                if (spriteNum == 2)
                    image = l2;

                break;
            case RIGHT:
                if (spriteNum == 1)
                   image = r1;
                if (spriteNum == 2)
                    image = r2;

                break;
        }

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);

    }

    public void setDefault()
    {
        x = 100;
        y = 100;
        speed = 2;

        direction = Direction.DOWN;
    }

    public void updatePos(int x, int y)
    {
        this.x += x;
        this.y += y;
    }

    public void updateSpeed(int speed) { this.speed = speed; }


    public void getPlayerImage()
    {
        try {
            r1 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-1.png"));
            r2 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-2.png"));
            l1 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-3.png"));
            l2 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-4.png"));
            d1 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-5.png"));
            d2 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-6.png"));
            u1 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-7.png"));
            u2 = ImageIO.read(getClass().getResourceAsStream("/res/sprites_p1/p-8.png"));
        
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSpeed() { return speed; }
}
