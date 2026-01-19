/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Ox child class
 * Special ability: immune to stuns
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Ox extends ZodiacAnimal {
    /**
     * Constructs a Ox animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Ox(PApplet app, int x, int y) {
        // from parent
        super(app, "ox", x, y);
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
}
