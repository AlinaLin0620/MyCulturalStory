/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Horse child class
 * Special ability: Speed grows over time
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Horse extends ZodiacAnimal {
    
    /**
     * Constructs a Horse animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Horse(PApplet app, int x, int y) {
        // from parent
        super(app, "horse", x, y);
    }
    /**
     * Override movement to apply exponetial growth to speed when boost is active
     * @param dx The direction of movement
     */
    @Override
    public void move(int dx) {
        // if boost active
        if (boostActive) {
            // Calculates bonus based on seconds passed since the start
            // for every 3 seconds that passed
            int bonus = (app.millis() - boostStartTime) / 3000;
            // boost grows by 1+ bonus
            x += dx * (1 + bonus);
        } else {
            // else standard movement
            x += dx;
        }
    }
}
