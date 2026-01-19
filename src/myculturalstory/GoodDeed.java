/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Represents a good deed and rewards a orb which is generated when you help a 
 * villager
 * This handles orb visuals and collision detection
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class GoodDeed {
    // initialize variables 
    private int x, y;
    private PImage orb;
    private boolean collected = false;
    
    /**
     * Constructs a GoodDeed orb at a specific location
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public GoodDeed(PApplet app, int x, int y) {
        // set up vars
        this.x = x;
        this.y = y;
        // laod in image
        orb = app.loadImage("images/goodOrb.png");
    }
    /**
     * Manages the orbs behaviour 
     * If not collected it will check for player collision
     * @param player The current player object
     * @param app PApple
     */
    public void update(ZodiacAnimal player, PApplet app) {
        // if not collected
        if(!collected) {
            // draw orb
            draw(app);
            // check if its been collected
            checkCollection(player);
        }
    }
    // draws orb if not collected yet
    public void draw(PApplet app) {
        if (!collected) {
            app.image(orb, x, y);
        }
    }
    /**
     * Returns if the orb has been collected or not
     * @return  true if collected, false otherwise
     */
    public boolean isCollected() {
        return collected;
    }
    /**
     * Determines if player is within the orb boundaries and can be collected
     * @param player The current player object
     */
    public void checkCollection(ZodiacAnimal player) {
        // checks if not collected and player x/y boundaries touch with the images x/y boundaries
        if (!collected 
                && player.getX() < x + orb.width 
                && player.getX() + player.getWidth() > x 
                && player.getY() < y + orb.height 
                && player.getY() + player.getHeight() > y) {
            // collected set to true
            collected = true;
            // let player recieve goodDeed
            player.receiveGoodDeed(this);
        }
    }
    /**
     * Resets orb to new position and clears collection status
     * @param newX New x cords
     * @param newY New y cords
     */
    public void reset(int newX, int newY) {
        // varaiables set
        x = newX;
        y = newY;
        // collect set a false
        collected = false;
    }
    
}
