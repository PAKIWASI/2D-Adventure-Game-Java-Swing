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

    private static final int MAX_TILES_TYPE = 10;
    
    
    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tilesType = new Tile[MAX_TILES_TYPE];
        tileMap = new int[gp.getMaxRows()][gp.getMaxCols()];

        loadTileMap("/res/tilemaps/tilemap_2.txt");
        getTileImage();
    }


    public void draw(Graphics2D g2)
    {
        int row = 0;
        int col = 0;
        
        int maxRows = gp.getMaxRows();
        int maxCols = gp.getMaxCols();
        int size = gp.getTileSize();

                // FIRST PASS: draw ground tiles
        while (row < maxRows) 
        {
            while (col < maxCols) 
            {
                int x = col * size;
                int y = row * size;
                int tileType = tileMap[row][col];
                
                // Draw ONLY normal ground tiles (skip trees)
                if (tileType != 4)
                {
                    g2.drawImage(tilesType[tileType].getImage(), x, y, size, size, null);
                } 
                else
                {
                    // Draw grass (or whatever default) under the tree
                    g2.drawImage(tilesType[0].getImage(), x, y, size, size, null);
                }
                col++;
            }
            row++;
            col = 0;
        }
    }

    public void drawTrees(Graphics2D g2)
    {
        int row = 0;
        int col = 0;
        
        int maxRows = gp.getMaxRows();
        int maxCols = gp.getMaxCols();
        int size = gp.getTileSize();

        // SECOND PASS: draw trees
        while (row < maxRows) 
        {
            while (col < maxCols) 
            {
                int x = col * size;
                int y = row * size;
                int tileType = tileMap[row][col];
                
                if (tileType == 4) 
                {
                    int treeSize = size * 2;
                    int treeX = x - (treeSize - size);
                    int treeY = y - (treeSize - size);
                    g2.drawImage(tilesType[tileType].getImage(), treeX, treeY, treeSize, treeSize, null);
                }
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
            tilesType[3].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/sand.png")));
        
            tilesType[4] = new Tile();
            tilesType[4].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/tree1.png")));

            tilesType[5] = new Tile();
            tilesType[5].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/wall.png")));

            tilesType[6] = new Tile();
            tilesType[6].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/stone.png")));

            tilesType[7] = new Tile();
            tilesType[7].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/path.png")));
        
            tilesType[8] = new Tile();
            tilesType[8].setImage(ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/path1.png")));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();    
        }
    }
}
