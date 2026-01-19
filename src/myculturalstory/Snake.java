/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Snake child class
 * Special ability: Smaller hit box
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Snake extends ZodiacAnimal {
    // variables 
    private boolean sliding = false;
    
    /**
     * Constructs a Snake animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Snake(PApplet app, int x, int y) {
        // from parent
        super(app, "snake", x, y);
    }
    
    /**
     * activates the slither ability and starts timer
     */
    @Override 
    public void useAbility() {
        // if boost not active
        if (!boostActive) {
            // boost active - true
            boostActive = true;
            // set boost starttime
            boostStartTime = app.millis();
            // set sliding to true
            sliding = true;
        }
    }
    
    /**
     * Exits sliding when boost ends
     */
    @Override 
    public void onBoostEnd() {
        // sliding is false
        sliding = false;
    }
    
    /**
     * Override getHeight to return a smaller value when sliding is true
     * @return 
     */
    @Override
    public int getHeight() {
        // sliding is true
        if (sliding)
            // return image height in half
            return image.height / 2;
        // else return regular image height
        return image.height;
    }
}
