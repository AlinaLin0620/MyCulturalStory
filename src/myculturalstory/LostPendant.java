/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Quest item for villager old man
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class LostPendant {
    // variables
    private int x, y;
    private PImage img;
    private boolean collected = false;
    private PApplet app;
    private boolean active = false; 

    /**
     * Constructor lost pendant object
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */ 
    public LostPendant(PApplet app, int x, int y) {
        // set up vars
        this.app = app;
        this.x = x;
        this.y = y;
        // load in image
        img = app.loadImage("images/jade_pendant.png");
    }

    /**
     * Updates the pendant state and only draws and check if collected when quest is active
     * @param player The current player object
     */
    public void update(ZodiacAnimal player) {
        // if not active or collected
        if (!active || collected) 
            // return nothing
            return;
        
        // else draw in pendant
        draw();
        // check if the pendant is collected
        checkCollection(player);
    }
    // draw pendant
    private void draw() {
        if (!collected) {
            app.image(img, x, y);
        }
    }
    /**
     * Checks if player image overlaps with pendant image 
     * @param player The current player object
     */
    public void checkCollection(ZodiacAnimal player) {
        // checks if not collected and player x/y boundaries touch with the images x/y boundaries
        if (!collected &&
            player.getX() < x + img.width &&
            player.getX() + player.getWidth() > x &&
            player.getY() < y + img.height &&
            player.getY() + player.getHeight() > y) {
            // set collected to true
            collected = true;
        }
    }
    /**
     * Returns the variable collected
     * @return if pendant is collected or not
     */
    public boolean isCollected() {
        return collected;
    }
     /**
     * Activates pendant to appear in game
     */
    public void activate() {
        active = true;
    }

    // getters
    /**
     * Gets the X position
     * @return x the horizontal position 
     */
    public int getX() {
        return x;
    }
    /**
     * Gets the Y position
     * @return y the vertical position 
     */
    public int getY() {
        return y;
    }
    /**
     * Gets the player width
     * @return image.width the width of player 
     */
    public int getWidth() {
        return img.width;
    }
    /**
     * Gets the player height
     * @return image.height the height of player 
     */
    public int getHeight() {
        return img.height;
    }
}

