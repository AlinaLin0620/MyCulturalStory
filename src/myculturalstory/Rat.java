/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Rat child class
 * Special ability: Protective shield from one stun and slight speed boost
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Rat extends ZodiacAnimal {
    // variable that tracks if shield has been used or not
    private boolean shieldUsed = false;
    
    /**
     * Constructs a Rat animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Rat(PApplet app, int x, int y) {
        // from parent
        super(app, "rat", x, y);
    }
    
    /**
     * Override movement to apply small speed boost
     * @param dx movement direction
     */
    @Override 
    public void move(int dx) {
        // if stunned
        if (stunned)
            // can't move if stunned
            return;
        // if boost active
        if (boostActive) {
            // increase speed diraction by 1.5
            x += dx * 1.5;
        } else {
            // else standard speed
            x += dx;
        }
    }
    
    /**
     * Overrides stun one time and checks for shield protection when boot is active
     */
    @Override 
    public void stun() {
        // if boost active and shiled not used
        if (boostActive && !shieldUsed) {
            // set shiled to true
            shieldUsed = true;
            // return without stunning
            return;
        }
        // else standard stun from parent
        super.stun();
    }
    
    /**
     * Resets the shield status when boost ends
     */
    @Override 
    public void onBoostEnd() {
        // set shield used to false
        shieldUsed = false;
    }
}
