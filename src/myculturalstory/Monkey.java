/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Monkey child class
 * Special ability: Defies gravity and is lifted in the air
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

// monkey class
public class Monkey extends ZodiacAnimal {
    
    /**
     * Constructs a Monkey animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Monkey(PApplet app, int x, int y) {
        // from parent
        super(app, "monkey", x, y);
    }
    /**
     * Overrides update and lets the character float when boost active
     */
    @Override
    public void update() {
        // if boost active
        if(boostActive) {
            // if character hasn't reached y = 200 yet
            if (y > 200)
                // bring player up to y 200
                y -= 5;
            // cancel falling physics
            velocityY = 0;
            // set on ground to true so jumping is possible
            onGround = true;
        }
        // run standard update from parent
        super.update();
    }
}
