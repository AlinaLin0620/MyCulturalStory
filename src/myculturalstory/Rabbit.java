/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Rabbit child class
 * Special ability: High jump
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Rabbit extends ZodiacAnimal {
    
    /**
     * Constructs a Rabbit animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Rabbit(PApplet app, int x, int y) {
        // from parent
        super(app, "rabbit", x, y);
    }
    
    /**
     * Overrides if touching the ground and not stunned and if boost is active
     */
    @Override 
    public void jump() {
        // if on ground and not stunned
        if (onGround && !stunned) {
            // if boost active
            if (boostActive) {
                // higher y velocity power
                velocityY = -18;
            // else standard velocity
            } else {
                velocityY = -12;
            }
            // set onground as being false
            onGround = false;
        }
    }
}

