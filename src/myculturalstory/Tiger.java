/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Tiger child class
 * Special ability: Big speed boost
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Tiger extends ZodiacAnimal {
    
    /**
     * Constructs a Tiger animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Tiger(PApplet app, int x, int y) {
        // from parent
        super(app, "tiger", x, y);
    }
    
    /**
     * Override move to apply larger speed boost if boost is active and player not stunend
     * @param dx movement direction
     */ 
    @Override 
    public void move(int dx) {
        // if stunned
        if (stunned) {
            // return null as player is stunned
            return;
        }
        // if boost active 
        if (boostActive) {
            // multipled movement direction by 2.5
            x += dx * 2.5;
        } else {
            // else standard movement speed
            x += dx;
        }
    }
}
