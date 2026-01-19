/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Sheep child class
 * Special ability: Longer glide
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Sheep extends ZodiacAnimal {
    
    /**
     * Constructs a Sheep animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Sheep(PApplet app, int x, int y) {
        // from parent
        super(app, "sheep", x, y);
    }
    
    /**
     * Overrides update to change physics of falling when boost active
     */
    @Override 
    public void update() {
        // if boost active
        if (boostActive) {
            // apply only 0.4 of the normal amount of gravity
            velocityY += GRAVITY *0.4f;
        } else {
            // standard gravity
            velocityY += GRAVITY;
        }
        // update vartical position based on velocity
        y += velocityY;
        
        // gorund collision
        if (y >= GROUND_Y) {
            // bring back to ground
            y = GROUND_Y;
            // stop movement
            velocityY = 0;
            // set on ground as true
            onGround = true;
        } else {
            // ellse animal is in the air
            onGround = false;
        }
        // standard stun logic from parent
        updateStun();
    }
}
