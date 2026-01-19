/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Pig child class
 * Special ability: immune to stuns and falls faster
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Pig extends ZodiacAnimal {
    /**
     * Constructs a Pig animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Pig(PApplet app, int x, int y) {
        // from parent
        super(app, "pig", x, y);
    }
    /**
     * Override update to increase falling speed when boost active
     */
    @Override 
    public void update() {
        // if boosted and not on ground
        if (boostActive && !onGround) {
            //increase velocity downwards
            velocityY += 2.0f;
        }
        // else parent class
        super.update();
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
