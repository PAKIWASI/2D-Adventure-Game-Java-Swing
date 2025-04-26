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
    private Tile[] tilesType; //0: grass, 1: water, ....
    private int[][] tileMap;

    private static final int MAX_TILES_TYPE = 4;
    
    
    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tilesType = new Tile[MAX_TILES_TYPE];
        tileMap = new int[gp.getMaxRows()][gp.getMaxCols()];

        loadTileMap("/res/tilemaps/tilemap_1.txt");
        getTileImage();
    }

    public void draw(Graphics2D g2)
    {
        int row = 0;
        int col = 0;
        
        int maxRows = gp.getMaxRows();
        int maxCols = gp.getMaxCols();

        int size = gp.getTileSize();

        while (row < maxRows)
        {
            while (col < maxCols)
            {
                // Multiply row and col by tileSize to get the correct pixel position
                int x = col * size;
                int y = row * size;
                g2.drawImage(tilesType[tileMap[row][col]].getImage(), x, y, size, size, null);
                col++;
            }
            row++;
            col = 0;
        }
    }

    private void loadTileMap(String path)
    {
        int row = 0;
        int col = 0;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path))))
        {
            String line;
            
            while ( ( line = reader.readLine() ) != null )
            {
                String[] values = line.split(" ");
                
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
            tilesType[0] = new Tile();
            tilesType[0].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/grass.png")));
            
            tilesType[1] = new Tile();
            tilesType[1].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/water.png")));
            
            tilesType[2] = new Tile();
            tilesType[2].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/dirt.png")));
            
            
            tilesType[3] = new Tile();
            tilesType[3].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/wall.png")));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();    
        }
    }
}
