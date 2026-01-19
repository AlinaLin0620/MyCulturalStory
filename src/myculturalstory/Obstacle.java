/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * A barrier for the player to overcome that stuns them for a few seconds
 * can be static or moving
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class Obstacle {
    // variables
    private int x, y;
    private int speed;
    private boolean moving;
    private boolean hasStunnedPlayer = false;
    private PImage image;
    
    /**
     * COnstructor for basic static obstacle
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */ 
    public Obstacle(PApplet app, int x, int y) {
        // set up vars
        this.x = x;
        this.y = y;
        this.moving = false;
        // load in image
        image = app.loadImage("images/stone.png");
    }
    
    /**
     * Overloaded constructor for a moving object
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     * @param speed The horizontal movement speed
     */
    public Obstacle(PApplet app, int x, int y, int speed) {
        // set up vars
        this.x = x;
        this.y = y;
        // added speed var
        this.speed = speed;
        this.moving = true;
        // load in image
        image = app.loadImage("images/stone.png");
    }
    /**
     * Updates the position of moving obstacle and resets if exits the screen
     */
    public void update() {
        // while moving
        if (moving) {
            // move obstacle left by the speed variable
            x -= speed;
        
            // reset when of screen
            if (x +image.width < 0) {
                // start just a bit further than screen to gie time for player to move
                x = 1200;
                // resets stun ability
                hasStunnedPlayer = false;
            }
        }
    }
    // draw in image
    public void draw(PApplet app) {
        app.image(image, x, y);
    }
    /**
     * Checks for collison between player image and obstacle image
     * @param player The current player object
     * @return true if images overlap otherwise false
     */
    public boolean touches(ZodiacAnimal player) {
        // checks if player x/y boundaries touch with the images x/y boundaries
        return player.getX() < x + image.width &&
               player.getX() + player.getWidth() > x &&
               player.getY() < y + image.height &&
               player.getY() + player.getHeight() > y;
    }  
    /**
     * Handles the logic when a collision does occur
     * @param player 
     */
    public void handleCollision(ZodiacAnimal player) {
        // if touching player
        if (touches(player)) {
            // if player isnt stunned already
            if (!hasStunnedPlayer) {
                // set player as stunned
                player.stun();
                // set stunned player to be true
                hasStunnedPlayer = true;       
            }
        } 
    }
}
