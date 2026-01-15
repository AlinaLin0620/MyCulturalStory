/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 *
 * @author alina
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class GoodDeed {
    // initialize variables 
    private int x, y;
    private PImage orb;
    private boolean collected = false;
    
    public GoodDeed(PApplet app, int x, int y) {
        this.x = x;
        this.y = y;
        orb = app.loadImage("images/goodOrb.png");
    }
    
    public void update(ZodiacAnimal player, PApplet app) {
        if(!collected) {
            draw(app);
            checkCollection(player);
        }
    }
    
    public void draw(PApplet app) {
        if (!collected) {
            app.image(orb, x, y);
        }
    }
    
    public boolean isCollected() {
        return collected;
    }
    
    public void checkCollection(ZodiacAnimal player) {
        if (!collected 
                && player.getX() < x + orb.width 
                && player.getX() + player.getWidth() > x 
                && player.getY() < y + orb.height 
                && player.getY() + player.getHeight() > y) {
            collected = true;
            player.receiveGoodDeed(this);
        }
    }
    
    public void reset(int newX, int newY) {
        x = newX;
        y = newY;
        collected = false;
    }
    
}
