/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Rooster child class
 * Special ability: 2X speed plus stun immunity
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Rooster extends ZodiacAnimal {
    
    /**
     * Constructs a Rooster animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Rooster(PApplet app, int x, int y) {
        // from parent
        super(app, "rooster", x, y);
    }
    
    
    /**
     * Overrides stun and give immunity if boost is active
     */ 
    @Override 
    public void stun() {
        // if boost is active
        if (boostActive) {
            // exit without apllying stun
            return;
        }
        // otherwise standard stun from parent
        super.stun();
    }
    /**
     * Override movement to apply small speed boost
     * @param dx movement direction
     */
    @Override
    public void move(int dx) {
        // if boost active
        if (boostActive) {
            // speed direction multiplied by 2X
            x += dx * 2; 
        } else {
            // standard movement from parent
            super.move(dx);
        }
    }
}
