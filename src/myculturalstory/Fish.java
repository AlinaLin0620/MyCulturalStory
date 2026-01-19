/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Quest item for villager girl
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
    // variables
    private int x, y;
    private PImage img;
    private boolean caught = false;
    private PApplet app;
    private boolean active = false;
    
    /**
     * Construct fish object
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Fish(PApplet app, int x, int y) {
        // set up vars
        this.app = app;
        this.x = x;
        this.y = y;
        // load in image
        img = app.loadImage("images/fish.png");
    }
    /**
     * Updates the fish state, and only draws and check if captured when quest is active
     * @param player The current player object
     * @param fishingKeyPressed checks if fishing key is being pressed or not
     */
    public void update(ZodiacAnimal player, boolean fishingKeyPressed) {
        // if not active or caught
        if (!active || caught) 
            // return nothing
            return;
        // else draw in fish
        draw();
        // check if the fish is caught
        checkCatch(player, fishingKeyPressed);
    }
    // draw in image of fish
    private void draw() {
        app.image(img, x, y);
    }
    /**
     * Determines if player is close enough to fish
     * @param player The current player object
     * @param fishingKeyPressed checks if fishing key is being pressed or not
     */
    private void checkCatch(ZodiacAnimal player, boolean fishingKeyPressed) {
        // set toughing var
        boolean touching =
            // if the players x area (left and right and anywhere in between) 
            // line up with the images then touching = true
            player.getX() + player.getWidth() > x &&
            player.getX() < x + img.width;
        // if touching is true and fishing key is being pressed
        if (touching && fishingKeyPressed) {
            // set caught to true
            caught = true;
            // turn off fishing key
            player.setFishingKey(false);
        }
    }
    /**
     * Returns the variable caught
     * @return caught if fish is caught or not
     */
    public boolean isCaught() {
        return caught;
    }
    /**
     * Activates fish to appear in game
     */
    public void activate() {
        active = true;
    }
}
