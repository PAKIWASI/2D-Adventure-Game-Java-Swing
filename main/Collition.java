/*package main;

import entity.Entity;
import tile.Tile;

public class Collition 
{
    public static void checkTile(GamePanel gp, Entity entity) 
    {                                                              
        int entityX = entity.getWorldX() + entity.getCollitionArea().x; // defining the collition area of the entity
        int entityY = entity.getWorldY() + entity.getCollitionArea().y;
        int entityWidth = entity.getCollitionArea().width;
        int entityHeight = entity.getCollitionArea().height;
 
        int entityLeftCol = entityX / gp.getTileSize();        // defining the 4 tiles the entity can move to  
        int entityRightCol = (entityX + entityWidth) / gp.getTileSize();
        int entityTopRow = entityY / gp.getTileSize();
        int entityBottomRow = (entityY + entityHeight) / gp.getTileSize();

        int[][] tileMap = gp.getTileMap();
        Tile[] tileTypes = gp.getTileTypes();

        switch (entity.getDirection()) 
        {
            case UP:          // for each direction, we only need to check 2 tiles
                int predictedY = entityY - entity.getSpeed();   // we predict where entity will land if it moves UP 
                int topRow = predictedY / gp.getTileSize();
                int tileNum1 = tileMap[topRow][entityLeftCol];    // we find the 2 tiles where entity can move to and check if any of them is collidable
                int tileNum2 = tileMap[topRow][entityRightCol];
                
                if (tileTypes[tileNum1].isCollidable() || tileTypes[tileNum2].isCollidable()) // if any one of the 2 is collidable, we set collition flag to true 
                    entity.setCollition(true);
                
                break;
            case DOWN:
                predictedY = entityY + entity.getSpeed();
                int bottomRow = (entityY + entityHeight + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = tileMap[bottomRow][entityLeftCol];
                tileNum2 = tileMap[bottomRow][entityRightCol];
                
                if (tileTypes[tileNum1].isCollidable() || tileTypes[tileNum2].isCollidable())
                    entity.setCollition(true);
                    
                break;
            case LEFT:
                int predictedX = entityX - entity.getSpeed();
                int leftCol = predictedX / gp.getTileSize();
                tileNum1 = tileMap[entityTopRow][leftCol];
                tileNum2 = tileMap[entityBottomRow][leftCol];

                if (tileTypes[tileNum1].isCollidable() || tileTypes[tileNum2].isCollidable())
                    entity.setCollition(true);
    
                break;
            case RIGHT:
                predictedX = entityX + entity.getSpeed();
                int rightCol = (entityX + entityWidth + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = tileMap[entityTopRow][rightCol];
                tileNum2 = tileMap[entityBottomRow][rightCol];

                if (tileTypes[tileNum1].isCollidable() || tileTypes[tileNum2].isCollidable())
                    entity.setCollition(true);
                
                break;
        }
    }
}*/