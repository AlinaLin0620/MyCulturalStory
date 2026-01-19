/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Quest item for villager boy
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class Firewood {
    // variables 
    private int x, y;
    private boolean collected = false;
    private PImage img;
    private PApplet app;
    private boolean active = false;
    
    /**
     * Constructor firewood object
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */ 
    public Firewood(PApplet app, int x, int y) {
        // set up vars
        this.app = app;
        this.x = x;
        this.y = y;
        // load in image
        img = app.loadImage("images/wood.png");
    }
    /**
     * Updates the firewood state, and only draws and check if collected when quest is active
     * @param player The current player object
     * @param collectKeyPressed checks if firewood key is being pressed or not
     */
    public void update(ZodiacAnimal player, boolean collectKeyPressed) {
        // if not active or collected
        if (!active || collected) 
            // return nothing
            return;
        // else draw in firewood
        draw();
        // check if the wood is collected
        checkPickup(player, collectKeyPressed);
    }
    // draw firewood
    private void draw() {
        app.image(img, x, y);
    }
    /**
     * Checks if images overlap and player is close enough to collect
     * @param player The current player object
     * @param collectKeyPressed checks if firewood key is being pressed or not
     */
    private void checkPickup(ZodiacAnimal player,boolean collectKeyPressed) {
        // checks if player x/y boundaries touch with the images x/y boundaries and that the collect key is pressed
        if (player.getX() < x + img.width &&
            player.getX() + player.getWidth() > x &&
            player.getY() < y + img.height &&
            player.getY() + player.getHeight() > y && 
            collectKeyPressed) {
            // set collected to tru
            collected = true;
        }
    }
    /**
     * Returns the variable collected
     * @return if firewood is collected or not
     */
    public boolean isCollected() {
        return collected;
    }
    /**
     * Activates firewood to appear in game
     */
    public void activate() {
        active = true;
    }
}
