/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Dog child class
 * Special ability: longer jumps
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Dog extends ZodiacAnimal {
    /**
     * Constructs a Dog animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Dog(PApplet app, int x, int y) {
        // from parent
        super(app, "dog", x, y);
    }
    /**
     * Overrides the movement to give speed boost if jumping and boosted
     * @param dx The direction of movement
     */
    @Override 
    public void move(int dx) {
        // checks fi the boost is currently active and dog is not on ground
        if (boostActive && !onGround) {
            // multiplies the standard movement by 4
            x += dx * 4;
        } else {
            // else uses standard movement from parent
            super.move(dx);
        }
    }
}
