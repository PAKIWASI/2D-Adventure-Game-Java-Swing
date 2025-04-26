package tile;

import java.awt.image.BufferedImage;

public class Tile 
{
    private BufferedImage image;
    private boolean collition = false;
    
    
    
    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage image) { this.image = image; }
    
    public boolean isCollition() { return collition; }
    public void setCollition(boolean collition) { this.collition = collition; }
}

