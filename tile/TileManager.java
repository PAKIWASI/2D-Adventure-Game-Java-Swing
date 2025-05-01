package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager 
{
    private GamePanel gp;
    private Tile[] tileTypes; //0: grass, 1: water, ....
    private int[][] tileMap; 

    private static final int MAX_TILES_TYPE = 12;
    
    
    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tileTypes = new Tile[MAX_TILES_TYPE];
        tileMap = new int[gp.getMaxWorldRows()][gp.getMaxWorldCols()];

        loadTileMap("/res/tilemaps/WorldMap.txt");
        getTileImage();
    }


    public void draw(Graphics2D g2) 
    {
        int size = gp.getTileSize();
        int playerWorldX = gp.getPlayer().getWorldX();
        int playerWorldY = gp.getPlayer().getWorldY();
        int screenWidth = gp.getWidth();
        int screenHeight = gp.getHeight();
        int playerScreenX = gp.getPlayer().getScreenX();
        int playerScreenY = gp.getPlayer().getScreenY();
    
        // Calculate the visible screen bounds in world coordinates
        int leftBound = playerWorldX - playerScreenX;
        int rightBound = leftBound + screenWidth;
        int topBound = playerWorldY - playerScreenY;
        int bottomBound = topBound + screenHeight;
    
        for (int worldRow = 0; worldRow < gp.getMaxWorldRows(); worldRow++)
        {
            for (int worldCol = 0; worldCol < gp.getMaxWorldCols(); worldCol++)
            {
                int tileType = tileMap[worldRow][worldCol]; // get type of each tile
                
                int worldX = worldCol * size;  // locate it's position on map
                int worldY = worldRow * size;
    
                // Check if the tile overlaps with the visible screen area
                if (worldX + size > leftBound && worldX < rightBound &&     // we only draw the visible tiles 
                    worldY + size > topBound && worldY < bottomBound)       // depending on camera position (with player at centre)
                {
                                                  
                    int screenX = worldX - playerWorldX + playerScreenX;  // determine tile's poition relative to player at centre 
                    int screenY = worldY - playerWorldY + playerScreenY;  // last value is offset as (0, 0) is centre now and not top left
    
                    g2.drawImage(tileTypes[tileType].getImage(), screenX, screenY, size, size, null);  // draw tile
                }
            }
        }
    }

    public void loadTileMap(String path)
    {
        int row = 0;
        int col = 0;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path))))
        {
            String line;
            
            while ( ( line = reader.readLine() ) != null )
            {
                String[] values = line.split(", ");
                
                while (col < tileMap[0].length)
                {
                    tileMap[row][col] = Integer.parseInt(values[col]);
                    col++;
                }
                row++;
                col = 0;
            }
        } 
        catch ( IOException e )
        {
            e.printStackTrace();;
        }    
    }


    private void getTileImage()
    {
        try {
            tileTypes[0] = new Tile();
            tileTypes[0].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/grass.png")));
            
            tileTypes[1] = new Tile();
            tileTypes[1].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/water.png")));
            tileTypes[1].setCollition(true);

            tileTypes[2] = new Tile();
            tileTypes[2].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/dirt.png")));
            
            tileTypes[3] = new Tile();
            tileTypes[3].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/sand.png")));
        
            tileTypes[4] = new Tile();
            tileTypes[4].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/tree.png")));
            tileTypes[4].setCollition(true);

            tileTypes[5] = new Tile();
            tileTypes[5].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/wall.png")));
            tileTypes[5].setCollition(true);            

            tileTypes[6] = new Tile();
            tileTypes[6].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/stone.png")));

            tileTypes[7] = new Tile();
            tileTypes[7].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/path.png")));
        
            tileTypes[8] = new Tile();
            tileTypes[8].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/path1.png")));
        
        } 
        catch (IOException e) 
        {
            e.printStackTrace();    
        }
    }
    
    public int[][] getTileMap() { return tileMap; }
    public Tile[] getTileTypes() { return tileTypes; }

    
}
