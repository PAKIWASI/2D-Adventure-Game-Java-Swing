package main;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.Tile;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTINGS
    private final int OgTileSize = 16; // pixels -> 16x16 blocks
    private final int scale = 3; // 48x48 on screen 
    private final int tileSize = OgTileSize * scale; // displayed on screen = 16 x 3 = 48 x 48

    private final int maxScreenRows = 12;  // the game has 12 x 16 tiles 
    private final int maxScreenCols = 16;   
    private final int SCREEN_HEIGHT = tileSize * maxScreenRows;  // 576 pixels  
    private final int SCREEN_WIDTH = tileSize * maxScreenCols;  // 768 pixels

    private final int FPS = 60;


    // WORLD SETTINGS
    private final int maxWorldRows = 64;
    private final int maxWorldCols = 64;
    private final int WORLD_HEIGHT = tileSize * maxWorldRows;
    private final int WORLD_WIDTH = tileSize * maxWorldCols;


    // For in-game time, just like irl time
    private Thread gameThread;

    private TileManager tileManager = new TileManager(this);

    private KeyHandler keyHandler = new KeyHandler();

    private Player player = new Player(this, keyHandler);

    //private Collition collChecker = new Collition(this); 

    // Constructor
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // prevent flickering (graphics are loaded completely before being drawn to screen)
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player.setDefault();
    }

    
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() // when gameThread is started, it automatically calls run()
    {
        double drawInterval = 1000000000 / FPS; // 1 billion nanosec = 1 sec -> / 60 = 0.01666 sec ->  one loop
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null)         // MAIN GAME LOOP
        {
            currentTime = System.nanoTime();        // FPS Setup

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1)
            {
                update();    // update variables based on current condition and user input

                repaint();   // draw to screen

                delta--;
            }

        } 
    }

    public void update()
    {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) // called automatically when repaint() is called
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;  // down-casting graphics obj to graphics 2d object 

        tileManager.draw(g2);       // make a draw method to handle complex drawing?
        
        player.draw(g2);
        
        g2.dispose();
    }

    //public void checkCollition(Entity entity)
    //{
   //     Collition.checkTile(this, entity);
   // }

    public Player getPlayer() { return player; }
    
    public int[][] getTileMap() { return tileManager.getTileMap(); }
    public Tile[] getTileTypes() { return tileManager.getTileTypes(); }
    
    public int getTileSize() { return tileSize; }
    public int getMaxWorldRows() { return maxWorldRows; }
    public int getMaxWorldCols() { return maxWorldCols; }
    
    @Override
    public int getWidth() { return SCREEN_WIDTH; }
    @Override
    public int getHeight() { return SCREEN_HEIGHT;  }

}
