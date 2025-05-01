package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity
{
    private GamePanel gp;
    private KeyHandler keyH;


    private final int screenX;   // position of player on screen is always fixed to middle, while the camera moves around the wworld map 
    private final int screenY;   // these are used for drawing on screen

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        screenX  = gp.getWidth() / 2;
        screenY = gp.getHeight() / 2;

        //collitionArea = new Rectangle(8, 16, gp.getTileSize() / 2, gp.getTileSize() / 2); // pos & size of collition area (not equal to image size 48x48 and doesnot cover entire player)

        setDefault();
        getPlayerImage();
    }

    public void update()  // gets called 60 times / sec (FPS)
    {
        // Direction update
        if (keyH.upPressed() == true) 
        {
            direction = Direction.UP;
            worldY -= speed;
        }
        
        if (keyH.downPressed() == true) 
        {
            direction = Direction.DOWN;
            worldY += speed;
        }   
        
        if (keyH.leftPressed() == true) 
        {
            direction = Direction.LEFT;
            worldX -= speed;
        }
        
        if (keyH.rightPressed() == true) 
        {
            direction = Direction.RIGHT;
            worldX += speed;
        }    
        
        // Collition
        /*collition = false;
        gp.checkCollition(this);
        
        if (collition)
        {
            switch(direction)
            {
                case UP: worldY += speed; break;
                case DOWN: worldY -= speed; break;
                case LEFT: worldX += speed; break;
                case RIGHT: worldX -= speed; break;
            }
        }*/
         
        // Sprite Update
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

            // After updating worldX and worldY, clamp the position
        int worldWidth = gp.getMaxWorldCols() * gp.getTileSize();
        int worldHeight = gp.getMaxWorldRows() * gp.getTileSize();
        int screenWidth = gp.getWidth();
        int screenHeight = gp.getHeight();

        // Clamp player position to keep the camera within the world
        worldX = Math.max(screenWidth / 2, Math.min(worldX, worldWidth - screenWidth / 2));
        worldY = Math.max(screenHeight / 2, Math.min(worldY, worldHeight - screenHeight / 2));
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

        int drawX = screenX - (gp.getTileSize() / 2); // centre the sprite (original starting point is top left)
        int drawY = screenY - (gp.getTileSize() / 2);

            // draw player to scrren at screenX, screenY point with 48x48 size
        g2.drawImage(image, drawX, drawY, gp.getTileSize(), gp.getTileSize(), null);

    }

    public void setDefault()
    {
        worldX = 32 * gp.getTileSize();
        worldY = 32 * gp.getTileSize();
        speed = 3;

        direction = Direction.DOWN;
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

    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }
}
