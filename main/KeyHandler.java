package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e)
    {
       
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = true;
        
        if (code == KeyEvent.VK_S) downPressed = true;

        if (code == KeyEvent.VK_A) leftPressed = true;

        if (code == KeyEvent.VK_D) rightPressed = true;
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;
        
        if (code == KeyEvent.VK_S) downPressed = false;

        if (code == KeyEvent.VK_A) leftPressed = false;

        if (code == KeyEvent.VK_D) rightPressed = false;
    }
    
    public boolean upPressed() { return upPressed; }
    public boolean downPressed() { return downPressed; }
    public boolean rightPressed() { return rightPressed; }
    public boolean leftPressed() { return leftPressed; }

}
